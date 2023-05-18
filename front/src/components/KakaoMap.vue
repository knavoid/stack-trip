<template>
  <div class="container">
    <h1>여행지 탐색</h1>
    <hr>
    <b-row>
      <b-col cols="8">
        <div id="map"></div>
      </b-col>
      <b-col>
        <div>
          <b-row>
            <b-col><b-form-select v-model="sidoCode" :options="sidoOptions" value-field="key" text-field="value" class="mb-3"></b-form-select></b-col>
            <b-col><b-form-select v-model="gugunCode" :options="gugunOptions" value-field="gugunCode" text-field="gugunName" class="mb-3"></b-form-select></b-col>
            <b-col><b-form-select v-model="contentTypeId" :options="contentOptions" value-field="key" text-field="value" class="mb-3"></b-form-select></b-col>
          </b-row>
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
                  <b-list-group-item>
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
        </div>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import http from "@/util/http-common.js";

export default {
  name: "KakaoMap",
  data() {
    return {
      currPosition: {
        latitude: null,
        longitude: null,
      },
      isPositionReady: false,
      markers: [],
      infowindow: null,

      sidoOptions: [
        { key: 1, value: "서울" },
        { key: 2, value: "인천" },
        { key: 3, value: "대전" },
        { key: 4, value: "대구" },
        { key: 5, value: "광주" },
        { key: 6, value: "부산" },
        { key: 7, value: "울산" },
        { key: 8, value: "세종특별자치시" },
        { key: 31, value: "경기도" },
        { key: 32, value: "강원도" },
        { key: 33, value: "충청북도" },
        { key: 34, value: "충청남도" },
        { key: 35, value: "경상북도" },
        { key: 36, value: "경상남도" },
        { key: 37, value: "전라북도" },
        { key: 38, value: "전라남도" },
        { key: 39, value: "제주도" },
      ],
      gugunOptions: [],

      contentOptions:[
        {key: 12, value: '관광지'},
        {key: 14, value: '문화시설'},
        {key: 15, value: '축제공연행사'},
        {key: 25, value: '여행코스'},
        {key: 28, value: '레포츠'},
        {key: 32, value: '숙박'},
        {key: 38, value: '쇼핑'},
        {key: 39, value: '음식점'},

      ],

      sidoCode: null,
      gugunCode: null,
      contentTypeId: null,
      title: "",
      searchResults: [],
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
    sidoCode:{
      handler(newSidoCode){
        this.getGugun(newSidoCode);
      },
      immediate: true,
    },
  },
  computed:{

  },  
  methods: {
    getGugun() {
      if (this.sidoCode) {
      http.get(`attraction/gugun?sidoCode=${this.sidoCode}`)
        .then(({ data }) => {
          console.log(data);
          this.gugunOptions = data;
        }).catch((err) => {
          console.log(err);
        })
    } else {
      this.gugunOptions = [];
    }
    return this.gugunOptions;
    },
    search(){
      http.get(`attraction?sidoCode=${this.sidoCode}&gugunCode=${this.gugunCode}&contentTypeId=${this.contentTypeId}&title=${this.title}`)
      .then(({data}) => {
        console.log(data);
        this.searchResults = data;
      }).catch((err) => {
        console.log(err);
      })

      this.searchResults = [
        {id: 1, name: '검색결과1'},
        {id: 2, name: '검색결과2'},
        {id: 3, name: '검색결과3'},
      ];
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
    
    changeSize(size) {
      const container = document.getElementById("map");
      container.style.width = `${size}px`;
      container.style.height = `${size}px`;
      this.map.relayout();
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
    displayInfoWindow() {
      if (this.infowindow && this.infowindow.getMap()) {
        //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
        this.map.setCenter(this.infowindow.getPosition());
        return;
      }

      var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

      this.infowindow = new kakao.maps.InfoWindow({
        map: this.map, // 인포윈도우가 표시될 지도
        position: iwPosition,
        content: iwContent,
        removable: iwRemoveable,
      });

      this.map.setCenter(iwPosition);
    },
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
    }
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 100%;
  height: 700px;
}

</style>
