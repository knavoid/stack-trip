<template>
  <div>
    <div>
      <h1 class="mb-4">여행지 게시판</h1>
      <b-row> 
        <b-col>
          <b-button @click="filterPosts(null)" variant="Link" class="text-decoration-none" size="big">전체 게시글</b-button>
        </b-col>
        <b-col v-for="board in boards" :key="board.type">
          <b-button  @click="filterPosts(board)" variant="Link" class="text-decoration-none" size="big">{{board.name}}</b-button>
        </b-col>
        <b-col
          ><b-button variant="dark" size="big"
              ><router-link class="text-white" to="/board/posting"
                >글쓰기</router-link
              ></b-button
            ></b-col
        >
      </b-row>
    </div>

    <hr/>
    <b-table v-if="filteredPosts.length > 0" striped hover :items="filteredPosts" @row-clicked="viewArticle"></b-table>
    <b-table v-else striped hover :items="items" @row-clicked="viewArticle"></b-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      boards: [
        {type: 1, name: "공지사항"},
        {type: 2, name: "이벤트"},
        {type: 3, name: "관광지 후기"},
        {type: 4, name: "자유게시판"},
      ],

      items: [
        {
          type: 2,
          subject: "필터테스트 2",
          user_code: "Macdonald",
          regtime: "23-05-15 09:32",
          views: 3,
        },
        {
          type: 3,
          subject: "필터테스트 3",
          user_code: "Macdonald",
          regtime: "23-05-15 09:32",
          views: 3,
        },
        {
          type: 4,
          subject: "필터테스트 4",
          user_code: "Macdonald",
          regtime: "23-05-15 09:32",
          views: 3,
        },
        {
          type: 2,
          subject: "필터테스트 2-2",
          user_code: "Macdonald",
          regtime: "23-05-15 09:32",
          views: 3,
        },
        {
          type: 1,
          subject: "필터테스트 1",
          user_code: "Macdonald",
          regtime: "23-05-15 09:32",
          views: 3,
        },
      ],

      selectedBoard: null,
      filteredPosts: [],

    };
  },
  methods:{
    filterPosts(board){
      if(board){
        console.log(board.type);
        //how to filter items by board.type?
        this.filteredPosts = this.items.filter(item => item.type === board.type);
      }else{
        this.filteredPosts = this.items;
      }
    },
    viewArticle(item){
      console.log(item);
      this.$router.push("/board/detail");
    }
  }
};
</script>

<style scoped>

</style>