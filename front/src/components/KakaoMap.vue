<template>
  <div class="container">
    <h1>ATTRACTION</h1>
    <hr />
    <b-row>
      <b-col cols="8">
        <div id="map"></div>
      </b-col>
      <b-col style="position: relative; height: 700px; overflow-y: auto">
        <div>
          <b-row>
            <b-col
              ><b-form-select
                v-model="sidoCode"
                :options="sidoOptions"
                value-field="key"
                text-field="value"
                class="mb-3"
              >
                <b-form-select-option :value="null">시/도</b-form-select-option>
              </b-form-select></b-col
            >
            <b-col
              ><b-form-select
                v-model="gugunCode"
                :options="gugunOptions"
                value-field="gugunCode"
                text-field="gugunName"
                class="mb-3"
              >
                <b-form-select-option :value="null">구/군</b-form-select-option>
              </b-form-select></b-col
            >
            <b-col
              ><b-form-select
                v-model="contentTypeId"
                :options="contentOptions"
                value-field="key"
                text-field="value"
                class="mb-3"
              >
                <b-form-select-option :value="null"
                  >관광지 유형</b-form-select-option
                >
              </b-form-select></b-col
            >
          </b-row>
          <b-input-group class="mb-3">
            <b-form-input
              v-model="title"
              placeholder="검색어를 입력하세요"
            ></b-form-input>
            <b-input-group-append>
              <b-button variant="primary" @click="search">검색</b-button>
            </b-input-group-append>
          </b-input-group>
          <hr />
          <div v-for="result in searchResults" :key="result.id">
            <b-row class="mb-3">
              <b-col>
                <!-- 이미지 로딩에 실패할 경우 대체 텍스트 또는 기본 이미지를 표시하도록 처리 -->
                <img
                  :src="result.image"
                  alt="Image"
                  class="img-fluid"
                  @error="handleImageError(result)"
                />
              </b-col>
              <b-col>
                <b-list-group>
                  <b-list-group-item
                    @click="moveCenter(result.latitude, result.longitude)"
                  >
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
      userCode: null,
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

      contentOptions: [
        { key: 12, value: "관광지" },
        { key: 14, value: "문화시설" },
        { key: 15, value: "축제공연행사" },
        { key: 25, value: "여행코스" },
        { key: 28, value: "레포츠" },
        { key: 32, value: "숙박" },
        { key: 38, value: "쇼핑" },
        { key: 39, value: "음식점" },
      ],

      sidoCode: null,
      gugunCode: null,
      contentTypeId: null,
      title: null,
      searchResults: [],
    };
  },
  created() {
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
  watch: {
    sidoCode: {
      handler(newSidoCode) {
        this.getGugun(newSidoCode);
      },
      immediate: true,
    },
    searchResults() {
      this.removeMarkers();
      this.createMarkers();
    },
  },
  computed: {},
  methods: {
    addBookmark(result) {
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
          if (this.userCode != null) {
            http
              .get(`/bookmark`, {
                headers: {
                  Authorization: `Bearer ${sessionStorage.getItem("token")}`,
                },
                params: {
                  attractionId: result.attractionId,
                  userCode: this.userCode,
                },
              })
              .then((response) => {
                console.log(response.data);
                if (response.data == true) {
                  alert("이미 북마크에 추가된 관광지입니다.");
                } else {
                  http
                    .post(`/bookmark`, data, {
                      headers: {
                        Authorization: `Bearer ${sessionStorage.getItem(
                          "token"
                        )}`,
                      },
                    })
                    .then(() => {
                      alert("북마크에 추가되었습니다.");
                    })
                    .catch((err) => {
                      console.log("북마크 추가 중 오류가 발생했습니다." + err);
                    });
                }
              });
          }
        })
        .catch((err) => {
          console.log("유저 정보를 가져오던 중 오류가 발생했습니다." + err);
          alert("북마크는 로그인 후 이용 가능합니다.");
        });
    },
    getGugun() {
      if (this.sidoCode) {
        http
          .get(`attraction/gugun`, {
            params: {
              sidoCode: this.sidoCode,
            },
          })
          .then(({ data }) => {
            console.log(data);
            this.gugunOptions = data;
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
        this.gugunOptions = [];
      }
      return this.gugunOptions;
    },
    search() {
      http
        .get(`attraction`, {
          params: {
            sidoCode: this.sidoCode,
            gugunCode: this.gugunCode,
            contentTypeId: this.contentTypeId,
            title: this.title,
          },
        })
        .then(({ data }) => {
          console.log(data);
          this.searchResults = data;

          //기존 마커 제거
          this.removeMarkers();

          //검색 결과 마커 생성
          this.createMarkers();

          //검색 결과가 있으면 첫번째 검색 결과로 지도 중심 이동
          if (this.searchResults.length > 0) {
            const firstResult = this.searchResults[0];
            const { latitude, longitude } = firstResult;
            this.map.setCenter(new kakao.maps.LatLng(latitude, longitude));
          }
        })
        .catch((err) => {
          console.log(err);
        });
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
      if (this.isPositionReady) {
        options.center = new kakao.maps.LatLng(
          this.currPosition.latitude,
          this.currPosition.longitude
        );
      } else {
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
    removeMarkers() {
      this.markers.forEach((marker) => marker.setMap(null));
      this.markers = [];
    },
    createMarker(latitude, longitude) {
      const marker = new kakao.maps.Marker({
        map: this.map,
        position: new kakao.maps.LatLng(latitude, longitude),
      });
      marker.setMap(this.map);
      this.markers.push(marker);
    },

    //사용자 위치 받아오는 함수 : 수정 필요
    getCurrentPosition() {
      if (!navigator.geolocation) {
        this.setAlert("위치 정보를 찾을 수 없습니다.");
      } else {
        navigator.geolocation.getCurrentPosition(
          this.getPositionValue,
          this.getPositionError
        );
      }
    },
    getPositionValue(value) {
      this.currPosition.latitude = value.coords.latitude;
      this.currPosition.longitude = value.coords.longitude;
      this.isPositionReady = true;
      this.setAlert("주소 확인 완료");
    },
    geolocationError() {
      this.setAlert("위치 정보를 사용할 수 없습니다.");
    },
    setAlert(msg) {
      console.log(msg);
    },
    //여기까지 사용자 위치 받아오는 함수

    createMarkers() {
      this.markers = [];

      this.searchResults.forEach((result) => {
        const position = new kakao.maps.LatLng(
          result.latitude,
          result.longitude
        );

        var overlayContainer = document.createElement("div");
        overlayContainer.className = "custom-overlay";

        var header = document.createElement("div");
        header.className = "overlay-header";
        var closeBtn = document.createElement("button");
        closeBtn.className = "close-btn";
        closeBtn.innerHTML = "&times;";
        var addBookmarkBtn = document.createElement("button");
        addBookmarkBtn.className = "add-bookmark";
        addBookmarkBtn.innerHTML = "❤";

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
        if (result.image == "") {
          image.src = "https://eumseongcci.korcham.net/images/no-image01.gif";
        } else {
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

        const marker = new kakao.maps.Marker({ position });

        kakao.maps.event.addListener(marker, "click", () => {
          overlay.setMap(this.map);
        });

        marker.setMap(this.map);
        this.markers.push(marker);
      });
    },
    moveCenter(latitude, longitude) {
      const moveLatLon = new kakao.maps.LatLng(latitude, longitude);
      this.map.panTo(moveLatLon);
    },
  },
};
</script>


<style scope>
#map {
  width: 100%;
  height: 700px;
}
.custom-overlay {
  width: 100%;
  height: 100%;
  /* padding: 10px; */
  background-color: #fff;
  border: 1px solid #ccc;
  box-shadow: 1px 1px 5px rgba(0, 0, 0, 0.3);
}

.overlay-header {
  background-color: gray;
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
  width: 14em;
  /* height: 200px; */
  object-fit: cover;
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
</style>
