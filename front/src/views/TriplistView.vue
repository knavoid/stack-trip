<template>
  <div>
    <h2>카카오맵을 띄워보자잇</h2>
    <div id="map">

    </div>
  </div>
</template>

<script>
export default {
  name: 'TriplistView',
  data(){
    return{
      map: null
    };
  },
  mounted(){
    if(window.kakao && window.kakao.maps){
      this.loadMap();
    }
    else{
      this.loadMapScript();
    }
  },
  methods:{
    loadMapScript(){
      const script = document.createElement('script');
      script.src = "//dapi.kakao.com/v2/maps/sdk.js?appkey=26bda7849e8c48d7db5c7837ea5ceefe"
      script.onload = () => window.kakao.maps.load(this.loadMap);

      document.head.appendChild(script);
    },
    loadMap(){
      const container = document.getElementById('map');
      const options = {
        center: new window.kakao.maps.LatLng(33.450701, 126.570667),
        level: 3
      };
      this.map = new window.kakao.maps.Map(container, options);
      this.loadMaker();
    },
  loadMaker(){
    const position = new window.kakao.maps.LatLng(33.450701, 126.570667);
    const marker = new window.kakao.maps.Marker({
      position
    });
    marker.setMap(this.map);
    }
  },
}
</script>

<style scoped>
  #map{
    width: 60%;
    height: 60%;
  }
</style>