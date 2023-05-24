<template>
  <div class="container">
    <h2 class="mt-5">질문 작성</h2>
    <b-form @submit="onSubmit">
      <b-form-group label-for="content">
        <b-form-textarea
          id="content"
          v-model="form.content"
          :rows="5"
        ></b-form-textarea>
      </b-form-group>
      <b-button class="mb-5" type="submit" variant="primary">등록</b-button>
    </b-form>
  </div>
</template>
  
  <script>
import http from "@/util/http-common.js";
export default {
  data() {
    return {
      userCode: null,
      userName: null,
      form: {
        userCode: 0,
        userName: "",
        content: "",
      },
    };
  },
  created() {
    this.getUserInfo();
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
          this.userName = data.name;
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
        this.form.userCode = this.userCode;
        this.form.userName = this.userName;
        console.log(this.form);
        http
          .post("/question", this.form, {
            headers: {
              Authorization: `Bearer ${sessionStorage.getItem("token")}`,
            },
          })
          .then(() => {
            this.$router.push("/qna");
          });
        alert("글이 등록되었습니다.");
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