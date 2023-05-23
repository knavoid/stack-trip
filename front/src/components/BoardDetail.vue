<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listArticle">목록</b-button>
      </b-col>
      <b-col class="text-right" v-if="article.userCode == userCode">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="deleteArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${article.postId}.
                ${article.subject} [${article.views}]</h3><div><h6>${article.userName}</div><div>${article.regTime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="article.content"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/util/http-common.js";
export default {
  name: "BoardDetail",
  data() {
    return {
      article: {
        postId: 0,
        userCode: 0,
        userName: "",
        subject: "",
        content: "",
        type: 0,
        regTime: "",
        views: 0,
      },
      userCode: 0,
    };
  },
  // computed() {
  //   if (this.article.content)
  //     return this.article.content.split("\n").join("<br/>");
  //   return "";
  // },
  created() {
    if (sessionStorage.getItem("token") === null) {
      this.$router.push("/login");
    } else {
      this.getDetail();
      this.getUserInfo();
    }
  },
  methods: {
    getDetail() {
      http
        .get(`/post/${this.$route.params.articleno}`, {
          headers: {
            Authorization: `Bearer ${sessionStorage.getItem("token")}`,
          },
        })
        .then(({ data }) => {
          this.article = data;
          http.put(`/post/${this.article.postId}/views`, null, {
            headers: {
              Authorization: `Bearer ${sessionStorage.getItem("token")}`,
            },
          });
        });
    },
    getUserInfo() {
      http
        .get(`/user`, {
          headers: {
            Authorization: `Bearer ${sessionStorage.getItem("token")}`,
          },
        })
        .then(({ data }) => {
          this.userCode = data.userCode;
        });
    },
    listArticle() {
      this.$router.push("/board");
    },
    moveModifyArticle() {
      this.$router.push(`/board/modify/${this.article.postId}`);
    },
    deleteArticle() {
      http
        .delete(`/post/${this.article.postId}`, {
          headers: {
            Authorization: `Bearer ${sessionStorage.getItem("token")}`,
          },
        })
        .then(() => {
          alert("글이 삭제되었습니다.");
          this.$router.push("/board");
        });
    },
  },
};
</script>

<style>
</style>