<template>
  <div class="container">
    <h1>게시글 작성</h1>
    <b-form @submit.prevent="submitForm">
      <b-form-group label="게시글 종류" label-for="type">
        <b-form-select
          id="type"
          v-model="form.type"
          :options="types"
        ></b-form-select>
      </b-form-group>
      <b-form-group label="제목" label-for="title">
        <b-form-input id="title" v-model="form.title"></b-form-input>
      </b-form-group>
      <b-form-group label="내용" label-for="content">
        <b-form-textarea
          id="content"
          v-model="form.content"
          :rows="5"
        ></b-form-textarea>
      </b-form-group>
      <b-button type="submit" variant="primary">작성 완료</b-button>
    </b-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        type: "",
        title: "",
        content: "",
      },
      types: ["공지사항", "이벤트", "관광지 후기", "자유게시판"],
    };
  },
};
</script>

<style scoped>
.container {
    margin-top: 20px;
    border: 1px solid #000000;
}
</style>