<template>
  <div>
    <h1>위시리스트</h1>
    <hr />
    <b-card-group class="d-flex flex-wrap">
      <b-card class="mb-3" v-for="(item, index) in wishList" :key="index">
        <b-card-img
          :src="item.image"
          :alt="item.title"
          @error="
            item.image = 'https://eumseongcci.korcham.net/images/no-image01.gif'
          "
        ></b-card-img>
        <b-card-body class>
          <h5 class="card-title">{{ item.title }}</h5>
          <p class="card-text">{{ item.address }}</p>
          <b-icon
            v-if="item.isBookmarked"
            icon="suit-heart-fill"
            variant="danger"
            scale="1.5"
            @click="removeBookmark(index, item.attractionId)"
          ></b-icon>
          <b-icon
            v-else
            icon="suit-heart"
            variant="danger"
            scale="1.5"
            @click="createBookmark(index, item.attractionId)"
          ></b-icon>
        </b-card-body>
      </b-card>
    </b-card-group>

    <div v-if="wishList.length === 0">위시리스트가 비어 있습니다.</div>
  </div>
</template>
  
<script>
import http from "@/util/http-common.js";
export default {
  data() {
    return {
      userCode: null,
      wishList: [],
    };
  },
  created() {
    if (sessionStorage.getItem("token") === null) {
      this.$router.push("/login");
    } else {
      this.getWishList();
    }
  },
  methods: {
    getWishList() {
      http
        .get(`/user`, {
          headers: {
            Authorization: `Bearer ${sessionStorage.getItem("token")}`,
          },
        })
        .then(({ data }) => {
          this.userCode = data.userCode;
          http
            .get(`/bookmark/${this.userCode}`, {
              headers: {
                Authorization: `Bearer ${sessionStorage.getItem("token")}`,
              },
            })
            .then(({ data }) => {
              data.forEach((element) => {
                element.isBookmarked = true;
                this.wishList.push(element);
              });
            });
        });
    },
    removeBookmark(index, attractionId) {
      this.wishList[index].isBookmarked = false;
      http.delete(
        `/bookmark?attractionId=${attractionId}&userCode=${this.userCode}`,
        {
          headers: {
            Authorization: `Bearer ${sessionStorage.getItem("token")}`,
          },
        }
      );
    },
    createBookmark(index, attractionId) {
      this.wishList[index].isBookmarked = true;
      http.post(
        `/bookmark?attractionId=${attractionId}&userCode=${this.userCode}`,
        { attractionId: attractionId, userCode: this.userCode },
        {
          headers: {
            Authorization: `Bearer ${sessionStorage.getItem("token")}`,
          },
        }
      );
    },
  },
};
</script>
  
<style scoped>
</style>