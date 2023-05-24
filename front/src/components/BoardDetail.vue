<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button
          variant="outline-primary"
          @click="listArticle"
          v-b-tooltip.hover
          title="목록으로"
          ><b-icon icon="list"></b-icon
        ></b-button>
      </b-col>
      <b-col class="text-right" v-if="article.userCode == userCode">
        <b-button
          variant="outline-info"
          size="m"
          @click="moveModifyArticle"
          class="mr-2"
          v-b-tooltip.hover
          title="글 수정하기"
          ><b-icon icon="pencil-square"></b-icon
        ></b-button>
        <b-button
          variant="outline-danger"
          size="m"
          @click="deleteArticle"
          title="질문 삭제하기"
          ><b-icon icon="trash"></b-icon
        ></b-button>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${article.subject} (${article.views})</h3><div><h6>작성자: ${article.userName}</div><div>${article.regTime}</h6></div>`"
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