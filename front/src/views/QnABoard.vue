<template>
  <div>
    <h1 class="mb-4">Q&A</h1>
    <b-row>
      <b-col cols="auto" class="ml-auto">
        <b-button variant="dark" size="big" v-b-tooltip.hover title="질문하기">
          <router-link class="text-white" to="/qna/qnaposting">
            <b-icon icon="pen"></b-icon>
          </router-link>
        </b-button>
      </b-col>
    </b-row>

    <hr />
    <b-table
      striped
      hover
      :items="items"
      :fields="fields"
      @row-clicked="viewArticle"
    >
    </b-table>
    <b-pagination
      v-model="currentPage"
      :total-rows="rows"
      align="center"
      pills
    ></b-pagination>
  </div>
</template>
  
  <script>
import http from "@/util/http-common.js";

export default {
  data() {
    return {
      rows: 10,
      currentPage: 1,
      items: [],
      fields: [
        { key: "questionId", label: "순번" },
        { key: "userName", label: "작성자" },
        { key: "content", label: "질문" },
        { key: "regTime", label: "작성일자" },
      ],
    };
  },
  created() {
    http.get("/question/list").then(({ data }) => {
      this.items = data;
    });
  },
  methods: {
    viewArticle(item) {
      console.log(item);
      this.$router.push(`/qna/detail/${item.questionId}`);
    },
  },
};
</script>
  
<style scoped>
</style>