<template>
  <div>
    <div>
      <h1 class="mb-4">여행지 게시판</h1>
      <b-row>
        <b-col>
          <b-button
            @click="filterPosts(null)"
            variant="Link"
            class="text-decoration-none"
            size="big"
            >전체 게시글</b-button
          >
        </b-col>
        <b-col v-for="board in boards" :key="board.type">
          <b-button
            @click="filterPosts(board)"
            variant="Link"
            class="text-decoration-none"
            size="big"
            >{{ board.name }}</b-button
          >
        </b-col>
        <b-col
          ><b-button variant="dark" size="big"
            ><router-link class="text-white" to="/board/posting"
              >글쓰기</router-link
            ></b-button
          ></b-col
        >
      </b-row>
    </div>

    <hr />
    <b-table
      v-if="filteredPosts.length > 0"
      striped
      hover
      :items="filteredPosts"
      @row-clicked="viewArticle"
    ></b-table>
    <b-table
      v-else
      striped
      hover
      :items="items"
      @row-clicked="viewArticle"
    ></b-table>
    <b-input-group id="searcharea" class="mb-3">
      <b-form-input v-model="keyword" placeholder="내용 검색" />
      <b-input-group-append>
        <b-button @click="search" variant="primary">검색</b-button>
      </b-input-group-append>
    </b-input-group>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
export default {
  data() {
    return {
      boards: [
        { type: 1, name: "공지사항" },
        { type: 2, name: "이벤트" },
        { type: 3, name: "관광지 후기" },
        { type: 4, name: "자유게시판" },
      ],
      items: [],
      selectedBoard: null,
      filteredPosts: [],
      keyword: null,
    };
  },
  created() {
    http.get("/post/list").then(({ data }) => {
      console.log(data);
      this.items = data;
    });
  },
  methods: {
    filterPosts(board) {
      if (board) {
        console.log(board.type);
        //how to filter items by board.type?
        this.filteredPosts = this.items.filter(
          (item) => item.type === board.type
        );
      } else {
        this.filteredPosts = this.items;
      }
    },
    viewArticle(item) {
      console.log(item);
      this.$router.push(`/board/detail/${item.postId}`);
    },
    search() {
      console.log("keyword" + this.keyword);
      http.get(`/post/list/search?keyword=${this.keyword}`).then(({ data }) => {
        console.log(data);
        this.filteredPosts = data;
      });
    },
  },
};
</script>

<style scoped>
#searcharea {
  width: 300px;
  margin: 0 auto;
}
</style>