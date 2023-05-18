<template>
    <div>
      <h1>위시리스트</h1>
      <hr>
      <b-card-group class="d-flex flex-wrap">
        <b-card class=" mb-3" v-for="item in wishlist" :key="item.id" style="width: 300px">
          <b-card-img :src="item.image" :alt="item.name"></b-card-img>
          <b-card-body class>
            <h5 class="card-title">{{ item.name }}</h5>
            <p class="card-text">{{ item.description }}</p>
            <b-button variant="danger" @click="removeFromWishlist(item.id)">위시리스트에서 제거</b-button>
          </b-card-body>
        </b-card>
      </b-card-group>
      
      <div v-if="wishlist.length === 0">위시리스트가 비어 있습니다.</div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        wishlist: [
          {
            id: 1,
            name: '상품 1',
            description: '이 상품은 매우 멋진 상품입니다.',
            image: 'http://tong.visitkorea.or.kr/cms/resource/21/2657021_image2_1.jpg'
          },
          {
            id: 2,
            name: '상품 2',
            description: '이 상품은 훌륭한 성능을 가진 상품입니다.',
            image: 'http://tong.visitkorea.or.kr/cms/resource/62/219162_image2_1.jpg'
          },
          {
            id: 3,
            name: '상품 3',
            description: '이 상품은 놀라운 기능을 제공하는 상품입니다.',
            image: 'http://tong.visitkorea.or.kr/cms/resource/83/1070183_image2_1.jpg'
          }
        ]
      };
    },
    methods: {
      removeFromWishlist(id) {
        // 위시리스트에서 특정 상품을 제거하는 메소드
        // 해당 상품을 wishlist 배열에서 제거합니다.
        this.wishlist = this.wishlist.filter(item => item.id !== id);
      }
    }
  };
  </script>
  
<style scoped>

</style>