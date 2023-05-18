<template>
    <div class="container">
      <h1 class="mt-5">QnA 작성</h1>
      <b-form @submit="onSubmit">
        <b-form-group label="내용" label-for="content">
          <b-form-textarea
            id="content"
            v-model="form.content"
            :rows="5"
          ></b-form-textarea>
        </b-form-group>
        <b-button class="mb-5" type="submit" variant="primary">글쓰기</b-button>
      </b-form>
    </div>
  </template>
  
  <script>
  import http from "@/util/http-common.js";
  export default {
    data() {
      return {
        form: {
            userCode: 5,
            userName: "김성훈",
            content: "",
        },
       
      };
    },
    methods: {
      onSubmit(evt) {
        evt.preventDefault();
        
        let err = true;
        if(this.form.content.length > 1){
            err = false;
        }
        if(err){
            alert("내용을 입력해주세요.");
            return;
        }else{
            alert("글이 등록되었습니다.");
            http.post("/question", this.form).then(() => {
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