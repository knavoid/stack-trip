<template>
  <div class="container">
    <h1>게시글 수정</h1>
    <b-form @submit.prevent="onSubmit">
      <b-form-group label="게시글 종류" label-for="type">
        <b-form-select
          id="type"
          v-model="selectedType"
          :options="types"
        ></b-form-select>
      </b-form-group>
      <b-form-group label="제목" label-for="subject">
        <b-form-input id="subject" v-model="form.subject"></b-form-input>
      </b-form-group>
      <b-form-group label="내용" label-for="content">
        <b-form-textarea
          id="content"
          v-model="form.content"
          :rows="5"
        ></b-form-textarea>
      </b-form-group>
      <b-button class="mb-5" type="submit" variant="primary">수정하기</b-button>
    </b-form>
  </div>
</template>
  
<script>
import http from "@/util/http-common.js";
export default {
  data() {
    return {
      form: {
        userCode: null,
        userName: null,
        subject: null,
        content: null,
        type: null,
        regTime: null,
        views: null,
      },
      types: ["공지사항", "이벤트", "관광지 후기", "자유게시판"],
      selectedType: "",
    };
  },
  created() {
    this.getArticle();
  },
  methods: {
    getArticle() {
      http
        .get(`/post/${this.$route.params.articleno}`, {
          headers: {
            Authorization: `Bearer ${sessionStorage.getItem("token")}`,
          },
        })
        .then(({ data }) => {
          this.form = data;
          this.selectedType = this.types[this.form.type - 1];
          console.log(this.selectedType);
        });
    },
    onSubmit(evt) {
      evt.preventDefault();

      let err = true;
      if (this.form.subject.length > 0 && this.form.content.length > 0) {
        err = false;
      }
      if (err) {
        alert("제목과 내용을 입력해주세요.");
        return;
      } else {
        this.form.type = this.types.indexOf(this.selectedType) + 1;
        console.log(this.form.type);
        alert("글이 수정되었습니다.");
        http
          .put(`/post/${this.$route.params.articleno}`, this.form, {
            headers: {
              Authorization: `Bearer ${sessionStorage.getItem("token")}`,
            },
          })
          .then(() => {
            this.$router.push("/board");
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
}
</style>