<template>
  <div class="container">
    <h2 class="mt-5">질문 수정</h2>
    <b-form @submit="onSubmit">
      <b-form-group label-for="content">
        <b-form-textarea
          id="content"
          v-model="form.content"
          :rows="5"
        ></b-form-textarea>
      </b-form-group>
      <b-button class="mb-5" type="submit" variant="primary">수정</b-button>
    </b-form>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
export default {
  data() {
    return {
      userCode: null,
      form: {
        questionId: this.$route.params.articleno,
        userCode: 0,
        userName: "",
        content: "",
      },
    };
  },
  created() {
    this.getUserInfo();
    this.getArticle();
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
    getArticle() {
      http
        .get(`/question/${this.$route.params.articleno}`, {
          headers: {
            Authorization: `Bearer ${sessionStorage.getItem("token")}`,
          },
        })
        .then(({ data }) => {
          this.form = data;
          console.log(data);
        });
    },
    onSubmit(evt) {
      evt.preventDefault();

      let err = true;
      if (this.form.content.length > 1) {
        err = false;
      }
      if (err) {
        alert("내용을 입력해주세요.");
        return;
      } else {
        alert("질문이 수정되었습니다.");
        http
          .put(`/question/${this.$route.params.articleno}`, this.form, {
            headers: {
              Authorization: `Bearer ${sessionStorage.getItem("token")}`,
            },
          })
          .then(() => {
            this.$router.push("/qna");
          });
      }
    },
  },
};
</script>
<style scoped>
.container {
  margin-top: 20px;
  border: 1px solid #000000;
  padding-left: 5%;
  padding-right: 5%;
}
</style>