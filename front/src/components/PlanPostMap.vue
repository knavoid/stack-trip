<template>
  <div class="container">
    <h1>계획 세우기</h1>
    <hr>
    
    <hr class="md-5">
    <b-row class="mb-3">
      <b-col>
        <div id="map"></div>
      </b-col>
      <b-col>
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
          </div>
      </b-col>
    </b-row>
    <div class="days mb-3"> <h3>1일차</h3></div>
    <div class="days mb-3"><h3>2일차</h3></div>
    <div class="days mb-3"><h3>3일차</h3></div>
  </div>
</template>

<script>
import http from "@/util/http-common.js";

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

      plans: [
          {
              title: "",
              description: "",
              begin_date: "",
              end_date: "",
          }
      ],

      sidoCode: null,
      gugunCode: null,
      contentTypeId: null,
      title: null,
      searchResults: [
        {
          image: "data:image/jpeg;base64,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",
          title: "집에가고싶어요",
          address: "서울특별시 관악구 신림동",
          latitude: 36.9929,
          longitude: 66.4564,
        }
      ],
    };
  },
  created(){
    this.getCurrentPosition();
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
    addBookmark(result){
      console.log(result);
      console.log(result.attractionId);
      http
      .get(`/user`, {
        headers: {
          Authorization: `Bearer ${sessionStorage.getItem("token")}`,
        },
      })
      .then(({ data }) => {
        this.userCode = data.userCode;
        console.log(this.userCode);
        data = {
          attractionId: result.attractionId,
          userCode: this.userCode,
        };
        console.log(data);
        if(this.userCode != null){
            http.get(`/bookmark`, {
              headers: {
                Authorization: `Bearer ${sessionStorage.getItem("token")}`,
              },
              params:{
                attractionId: result.attractionId,
                userCode: this.userCode,
              }
            })
            .then((response) => {
              console.log(response.data);
              if(response.data == true){
                alert("이미 북마크에 추가된 관광지입니다.");
              }else{
                http.post(`/bookmark`, data, {            
                headers:{
                  Authorization: `Bearer ${sessionStorage.getItem("token")}`,
                }
                })
                .then(() => {
                  alert("북마크에 추가되었습니다.");
                })
                .catch((err) => {
                  console.log("북마크 추가 중 오류가 발생했습니다." + err);
                });
              }
            })
        }
        })
        .catch((err) => {
          console.log("유저 정보를 가져오던 중 오류가 발생했습니다." + err);
          alert("북마크는 로그인 후 이용 가능합니다.");
        });
        
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
        closeBtn.innerHTML = "&times;";
        var addBookmarkBtn = document.createElement("button");
        addBookmarkBtn.className = "add-bookmark";
        addBookmarkBtn.innerHTML = '❤';

        closeBtn.addEventListener("click", () => {
          overlay.setMap(null);
        });

        addBookmarkBtn.addEventListener("click", () => {
          //북마크 추가 메소드 구현
          this.addBookmark(result);
        });

        header.appendChild(closeBtn);
        header.appendChild(addBookmarkBtn);

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
      width: 100%;
      height: 50vh;
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
      width: 20em;
    }

    .overlay-info {
      flex-grow: 1;
    }

    .overlay-title {
      font-size: 16px;
      font-weight: bold;
      margin-bottom: 5px;
    }

    .overlay-address {
      font-size: 14px;
    }

    .days{
      background-color: rgb(39, 39, 39);
      color: #fff;
      text-align: left;
      padding: 1%;
    }
    
</style>
