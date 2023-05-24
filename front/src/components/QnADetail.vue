<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button
          variant="outline-primary"
          v-b-tooltip.hover
          title="목록으로"
          @click="listArticle"
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
          title="질문 수정하기"
        >
          <b-icon icon="pencil-square"></b-icon>
        </b-button>
        <b-button
          variant="outline-danger"
          size="m"
          @click="deleteArticle"
          v-b-tooltip.hover
          title="질문 삭제하기"
        >
          <b-icon icon="trash"></b-icon>
        </b-button>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<div><h5>${article.userName}님의 질문</div><div>${article.regTime}</h5></div>`"
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
    <b-form @submit="onSubmitAnswer">
      <b-form-group class="text-left" label-for="content">
        <b-form-textarea
          id="content"
          v-model="newAnswer.content"
          :rows="5"
        ></b-form-textarea>
      </b-form-group>
      <b-button class="" type="submit" variant="primary">답변등록</b-button>
    </b-form>
    <hr />
    <div>
      <div v-for="answer in article.answers" :key="answer.answerId">
        <b-card
          :header-html="`<span>${answer.userName}님의 답변</span><br/><span>${answer.regTime}</span>`"
          class="text-left mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="answer.content"></div>
          </b-card-body>

          <b-button
            v-if="answer.userCode == userCode"
            id="delete-answer-button"
            variant="outline-danger"
            size="sm"
            @click="DeleteAnswer(answer)"
            >삭제</b-button
          >
        </b-card>
      </div>
    </div>
  </b-container>
</template>

<script>
import http from "@/util/http-common.js";
export default {
  name: "BoardDetail",
  data() {
    return {
      userCode: null,
      article: {
        questionId: 0,
        userCode: 0,
        content: "",
        regTime: "",
        answers: [],
      },
      newAnswer: {
        questionId: this.$route.params.articleno,
        userCode: 0,
        content: "",
      },
    };
  },

  created() {
    this.getUserInfo();
    this.getDetail();
  },
  methods: {
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
    getDetail() {
      http
        .get(`/question/${this.$route.params.articleno}`, {
          headers: {
            Authorization: `Bearer ${sessionStorage.getItem("token")}`,
          },
        })
        .then(({ data }) => {
          this.article = data;
          console.log(data);
        })
        .catch(() => {
          this.$router.push("/login");
        });
      // console.log(this.article);
    },
    listArticle() {
      this.$router.push("/qna");
    },
    moveModifyArticle() {
      this.$router.push(`/qna/modify/${this.article.questionId}`);
    },
    deleteArticle() {
      //여기에 article을 삭제하는 함수를 구현한다.
      alert("질문이 삭제되었습니다.");
      http
        .delete(`/question/${this.$route.params.articleno}`, {
          headers: {
            Authorization: `Bearer ${sessionStorage.getItem("token")}`,
          },
        })
        .then(() => {
          this.$router.push("/qna");
        });
    },
    onSubmitAnswer(evt) {
      evt.preventDefault();
      this.newAnswer.userCode = this.userCode;
      console.log(this.newAnswer);
      let err = true;
      if (this.newAnswer.content.length >= 1) {
        err = false;
      }
      if (err) {
        alert("내용을 입력해주세요.");
        return;
      } else {
        console.log(this.newAnswer);
        alert("답변이 등록되었습니다.");
        http
          .post(`/answer`, this.newAnswer, {
            headers: {
              Authorization: `Bearer ${sessionStorage.getItem("token")}`,
            },
          })
          .then((response) => {
            this.newAnswer.content = ""; // 댓글 작성 후 내용 초기화
            this.newAnswer.userName = response.data.userName; // 작성자 이름 설정
            this.newAnswer.regTime = response.data.regTime; // 작성 시간 설정
            this.getDetail(); // 글 상세 정보 다시 가져오기
          })
          .catch((err) => {
            console.log("error :" + err);
          });
      }
    },
    DeleteAnswer(answer) {
      alert("답변이 삭제되었습니다.");
      http
        .delete(`/answer/${answer.answerId}`, {
          headers: {
            Authorization: `Bearer ${sessionStorage.getItem("token")}`,
          },
        })
        .then(() => {
          this.getDetail();
        });
    },
  },
};
</script>

<style>
#delete-answer-button {
  width: 50px;
  align-self: center;
  margin-bottom: 10px;
}
</style>