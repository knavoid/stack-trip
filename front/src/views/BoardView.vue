<template>
  <div class="container">
    <div>
      <b-row>
        <b-col><span>전체 게시글</span></b-col>
        <b-col><span>공지사항</span></b-col>
        <b-col><span>이벤트</span></b-col>
        <b-col><span>관광지 후기</span></b-col>
        <b-col><span>자유게시판</span></b-col>
        <b-col
          ><b-button variant="dark" size="big"
              ><router-link class="text-white" to="/board/posting"
                >글쓰기</router-link
              ></b-button
            ></b-col
        >
      </b-row>
    </div>

    <hr />
    <b-table striped hover :items="items"></b-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: [
        {
          type: "자유",
          subject: "제목예시",
          user_code: "Macdonald",
          regtime: "23-05-15 09:32",
          views: 3,
        },
        {
          type: "자유",
          subject: "제목예시",
          user_code: "Macdonald",
          regtime: "23-05-15 09:32",
          views: 3,
        },
        {
          type: "자유",
          subject: "제목예시",
          user_code: "Macdonald",
          regtime: "23-05-15 09:32",
          views: 3,
        },
        {
          type: "자유",
          subject: "제목예시",
          user_code: "Macdonald",
          regtime: "23-05-15 09:32",
          views: 3,
        },
        {
          type: "자유",
          subject: "제목예시",
          user_code: "Macdonald",
          regtime: "23-05-15 09:32",
          views: 3,
        },
      ],
    };
  },
};
</script>

<style>
.container {
  margin-top: 20px;
}
</style>