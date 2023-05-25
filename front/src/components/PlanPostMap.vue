<template>
  <div class="container">
    <h1>여행 계획 세우기</h1>
    <hr>
    <h5>제목: {{ this.nowTrip.title }}</h5>
    <h5>여행 설명: {{ this.nowTrip.description }}</h5>
    <h5>출발일: {{ this.nowTrip.beginDate }}</h5>
    <h5>종료일: {{ this.nowTrip.endDate }}</h5>
    <b-button class="deleteBtn mr-3" variant="danger" @click="deleteTrip">삭제</b-button>
    <hr class="md-5">
    <b-row class="mb-3">
      <b-col>
        <div id="map"></div>
      </b-col>
      <b-col style="position:relative; height:400px; overflow-y:auto">
        <b-input-group class="mb-3">
          <b-form-input v-model="title" placeholder="검색어를 입력하세요"></b-form-input>
          <b-input-group-append>
            <b-button variant="primary" @click="search">검색</b-button>
          </b-input-group-append>
        </b-input-group>
        <hr>
        <div v-for="result in searchResults" :key="result.id">
            <b-row class="mb-3">
              <b-col>
              <!-- 이미지 로딩에 실패할 경우 대체 텍스트 또는 기본 이미지를 표시하도록 처리 -->
              <img :src="result.image" alt="Image" class="img-fluid" @error="handleImageError(result)">
              </b-col>
              <b-col>
                <b-list-group>
                  <b-list-group-item @click="moveCenter(result.latitude, result.longitude)">
                    <b-row align-v="center">
                      <b-col cols="auto">
                        <strong>{{ result.title }}</strong>
                      </b-col>
                      <b-col cols="auto" class="ml-auto">
                        {{ result.address }}
                      </b-col>
                    </b-row>
                  </b-list-group-item>
                </b-list-group>
              </b-col>
            </b-row>
            <hr>
          </div>
      </b-col>
    </b-row>
    <hr>
    <h3>가고싶은 여행지</h3>
    <!-- <div v-for="item in addList" :key="item.attractionId">
      <b-row class="mb-3">
        <b-col>
          <img :src="item.image" alt="Image" class="img-fluid">
        </b-col>
        <b-col>
          <b-list-group>
            <b-list-group-item @click="moveCenter(item.latitude, item.longitude)">
              <b-row align-v="center">
                <b-col cols="auto">
                  <strong>{{ item.title }}</strong>
                </b-col>
                <b-col cols="auto" class="ml-auto">
                  {{ item.address }}
                </b-col>
              </b-row>
            </b-list-group-item>
          </b-list-group>
        </b-col>
      </b-row>
      <hr>
    </div> -->
    <div v-for="day in tripDays" :key="day" class="days mb-3">
      <h3>{{ day }}일차</h3>
    </div>
    <b-button class="mt-3" variant="primary">저장</b-button>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
import moment from "moment";

export default {
  name: "PlanMap",
  data() {
    return {
      userCode: null,
      currPosition: {
        latitude: null,
        longitude: null,
      },
      isPositionReady: false,
      markers: [],

      tripDays:[],
      plans: [
          {
              title: "",
              description: "",
              begin_date: "",
              end_date: "",
          }
      ],
      nowTrip:{
          tripId: 0,
          userCode: 0,
          title: "",
          description: "",
          beginDate: "",
          endDate: "",
          plans: null
      },

      sidoCode: null,
      gugunCode: null,
      contentTypeId: null,
      title: null,
      searchResults: [],
      addList: [],
    };
  },
  created(){
    this.getCurrentPosition();
    this.getDetail();
    
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=26bda7849e8c48d7db5c7837ea5ceefe";
      document.head.appendChild(script);
    }
  },
  watch:{
    searchResults(){
      this.removeMarkers();
      this.createMarkers();
    }
  },
  computed:{
  },  
  methods: {
    deleteTrip(){
      http.delete(`trip/${this.$route.params.articleno}`)
      .then(({data}) => {
        console.log(data);
        alert("삭제되었습니다.");
        this.$router.push(`/plan/${this.userCode}`);
      }).catch((err) => {
        console.log(err);
      })
    },  
    getDetail(){
      http.get(`trip/${this.$route.params.articleno}`)
      .then(({data}) => {
        console.log(data);
        this.nowTrip = data;
        this.calculateTripDays();
      }).catch((err) => {
        console.log(err);
      })
    },
    calculateTripDays() {
      const beginDate = moment(this.nowTrip.beginDate);
      const endDate = moment(this.nowTrip.endDate);
      const days = endDate.diff(beginDate, 'days') + 1;
      this.tripDays = Array.from({ length: days }, (_, index) => index + 1);
    },

    
    search(){
      http.get(`attraction`,{
        params:{
          title: this.title,
        }
      })
      .then(({data}) => {
        console.log(data);
        this.searchResults = data;

        //기존 마커 제거
        this.removeMarkers();

        //검색 결과 마커 생성
        this.createMarkers();

        //검색 결과가 있으면 첫번째 검색 결과로 지도 중심 이동
        if(this.searchResults.length > 0){
          const firstResult = this.searchResults[0];
          const { latitude, longitude } = firstResult;
          this.map.setCenter(new kakao.maps.LatLng(latitude, longitude));
        }

      }).catch((err) => {
        console.log(err);
      })
      this.createMarkers();
    },
    handleImageError(result) {
      // 이미지 로딩에 실패한 경우 대체 텍스트 또는 기본 이미지를 설정합니다.
      result.image = "https://eumseongcci.korcham.net/images/no-image01.gif";
    },
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: {},
        level: 5,
      };
      if(this.isPositionReady){
        options.center = new kakao.maps.LatLng(this.currPosition.latitude, this.currPosition.longitude);
      }else{
        options.center = new kakao.maps.LatLng(33.450701, 126.570667); 
        options.level = 5;
      }

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
    },
    
    displayMarker(markerPositions) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(...position)
      );

      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            })
        );

        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);
      }
    },
    removeMarkers(){
      this.markers.forEach((marker) => marker.setMap(null));
      this.markers = [];
    },
    createMarker(latitude, longitude){
      const marker = new kakao.maps.Marker({
        map: this.map,
        position: new kakao.maps.LatLng(latitude, longitude),
      });
      marker.setMap(this.map);
      this.markers.push(marker);
    },
    
    //사용자 위치 받아오는 함수 : 수정 필요
    getCurrentPosition(){
      if(!navigator.geolocation){
        this.setAlert('위치 정보를 찾을 수 없습니다.');
      } else{
        navigator.geolocation.getCurrentPosition(this.getPositionValue, this.getPositionError)
      }
    },
    getPositionValue(value){
      this.currPosition.latitude = value.coords.latitude;
      this.currPosition.longitude = value.coords.longitude;
      this.isPositionReady = true;
      this.setAlert('주소 확인 완료');
    },
    geolocationError(){
      this.setAlert("위치 정보를 사용할 수 없습니다.");
    },
    setAlert(msg){
      console.log(msg);
    },
    //여기까지 사용자 위치 받아오는 함수

    addPlanList(result){
      console.log(result);
      this.planList.push({result});

    },
    createMarkers(){
      this.markers = [];

      this.searchResults.forEach((result) => {
        const position = new kakao.maps.LatLng(result.latitude, result.longitude);

        var overlayContainer = document.createElement("div");
        overlayContainer.className = "custom-overlay";

        var header = document.createElement("div");
        header.className = "overlay-header";
        var closeBtn = document.createElement("button");
        closeBtn.className = "close-btn";
        closeBtn.innerHTML = "❌";
        var addPlanBtn = document.createElement("button");
        addPlanBtn.className = "add-btn";
        addPlanBtn.innerHTML = '➕';

        closeBtn.addEventListener("click", () => {
          overlay.setMap(null);
        });

        addPlanBtn.addEventListener("click", () => {
          //계획 생성 api 적용;
          this.addPlanList(result);
        });

        header.appendChild(closeBtn);
        header.appendChild(addPlanBtn);

        overlayContainer.appendChild(header);

        var overlayContent = document.createElement("div");
        overlayContent.className = "overlay-content";

        var overlayImage = document.createElement("div"); 
        overlayImage.className = "overlay-image";

        var image = document.createElement("img");
        image.className = "overlay-image";
        if(result.image == ''){
          image.src = "https://eumseongcci.korcham.net/images/no-image01.gif";
        }else{
          image.src = result.image;
        }
        
        image.alt = "image";
        overlayImage.appendChild(image);

        var br = document.createElement("br");

        var overlayInfo = document.createElement("div");
        overlayInfo.className = "overlay-info";

        var overlayTitle = document.createElement("h3");
        overlayTitle.className = "overlay-title";
        overlayTitle.innerHTML = result.title;
        var overlayAddress = document.createElement("p");
        overlayAddress.className = "overlay-address";
        overlayAddress.innerHTML = result.address;

        overlayInfo.appendChild(overlayTitle);
        overlayInfo.appendChild(overlayAddress);

        overlayContent.appendChild(overlayImage);
        overlayContent.appendChild(br);
        overlayContent.appendChild(overlayInfo);
        overlayContainer.appendChild(overlayContent);

        var overlay = new kakao.maps.CustomOverlay({
          content: overlayContainer,
          position: new kakao.maps.LatLng(result.latitude, result.longitude),
          yAnchor: 1,
        });

        const marker = new kakao.maps.Marker({position});

        kakao.maps.event.addListener(marker, "click", () => {
          overlay.setMap(this.map);
        });

        marker.setMap(this.map);
        this.markers.push(marker);
      })
    },
    moveCenter(latitude, longitude){
      const moveLatLon = new kakao.maps.LatLng(latitude, longitude);
      this.map.panTo(moveLatLon);
    },

  },
};
</script>


<style>
  #map {
    width: 100%;
    height: 50vh;
  }
  .custom-overlay {
      width: 150px;
      height: auto;
      /* padding: 10px; */
      background-color: #fff;
      border: 1px solid #ccc;
      box-shadow: 1px 1px 5px rgba(0, 0, 0, 0.3);
    }

    .overlay-header {
      background-color:gray;
      display: flex;
      justify-content: space-between;
      align-items: left;
      margin-bottom: 10px;
    }

    .overlay-header button {
      background-color: transparent;
      /* border: none; */
      cursor: pointer;
      font-size: 14px;
      font-weight: bold;
    }

    .overlay-content {
      /* display: flex; */
      margin: 1vh;
    }

    .overlay-image {
      width: 100%;
      height: 100%;
      object-fit: cover;
    }

    .overlay-info {
      flex-grow: 1;
    }

    .overlay-title {
      font-size: 16px;
      font-weight: bold;
      margin-bottom: 5px;
      word-break: break-all;
      overflow-wrap: break-word;
      display: -webkit-box;
      -webkit-line-clamp: 2;
      -webkit-box-orient: vertical;
      line-height: 1.2;
      max-height: 2.4em;
      overflow: hidden;
      text-overflow: ellipsis;
    }

    .overlay-address {
      font-size: 14px;
      word-break: break-all;
      overflow-wrap: break-word;
      display: -webkit-box;
      -webkit-line-clamp: 3;
      -webkit-box-orient: vertical;
      line-height: 1.2;
      max-height: 3.6em;
      overflow: hidden;
      text-overflow: ellipsis;
    }

    .days{
      background-color: rgb(39, 39, 39);
      color: #fff;
      text-align: left;
      padding: 1%;
    }
    
</style>
