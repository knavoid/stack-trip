<template>
  <div class="container">
    <h1>게시글 작성</h1>
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
      <b-button class="mb-5" type="submit" variant="primary"
        >작성 완료</b-button
      >
    </b-form>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
export default {
  data() {
    return {
      form: {
        userCode: 0,
        userName: "",
        subject: "",
        content: "",
        type: 0,
      },
      types: ["공지사항", "이벤트", "관광지 후기", "자유게시판"],
      selectedType: "",
    };
  },
  created() {
    if (sessionStorage.getItem("token") === null) {
      this.$router.push("/login");
    } else {
      this.getUserInfo();
    }
  },
  methods: {
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
        alert("글이 등록되었습니다.");
        http
          .post("/post", this.form, {
            headers: {
              Authorization: `Bearer ${sessionStorage.getItem("token")}`,
            },
          })
          .then(() => {
            this.$router.push("/board");
          });
      }
    },
    getUserInfo() {
      http
        .get(`/user`, {
          headers: {
            Authorization: `Bearer ${sessionStorage.getItem("token")}`,
          },
        })
        .then(({ data }) => {
          this.form.userCode = data.userCode;
          console.log(this.form.userCode);
        });
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