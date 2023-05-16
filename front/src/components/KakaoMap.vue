<template>
  <div class="container">
    <h1>여행지 탐색</h1>
    <hr>
    <b-row>
      <b-col cols="8"><div id="map"></div></b-col>
      <b-col>
        <div>
          <b-row>
            <b-col><b-form-select v-model="sidoCode" :options="getSido" class="mb-3"></b-form-select></b-col>
            <b-col><b-form-select v-model="gugunCode" :options="getGugun" class="mb-3"></b-form-select></b-col>
            <b-col><b-form-select v-model="contentTypeId" :options="getContentTypeId" class="mb-3"></b-form-select></b-col>
          </b-row>
          <b-input-group class="mb-3">
            <b-form-input v-model="title" placeholder="검색어를 입력하세요"></b-form-input>
            <b-input-group-append>
              <b-button variant="primary" @click="search">검색</b-button>
            </b-input-group-append>
          </b-input-group>
          <hr>
          <b-list-group>
            <b-list-group-item v-for="result in searchResults" :key="result.id">
              {{result.id}} {{ result.name }}
            </b-list-group-item>
          </b-list-group>
        </div>
      </b-col>
    </b-row>
  </div>
</template>

<script>
export default {
  name: "KakaoMap",
  data() {
    return {
      currPosition: {},
      isPositionReady: false,
      markers: [],
      infowindow: null,

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
  computed:{
    getSido(){
      return ['옵션1', '옵션2', '옵션3'];
    },
    getGugun(){
      return ['옵션1', '옵션2', '옵션3'];
    },
    getContentTypeId(){
      return ['옵션1', '옵션2', '옵션3'];
    },

  },  
  methods: {
    search(){
      this.searchResults = [
        {id: 1, name: '검색결과1'},
        {id: 2, name: '검색결과2'},
        {id: 3, name: '검색결과3'},
      ];
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
