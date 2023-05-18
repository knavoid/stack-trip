<template>
    <div>
      
        <h1 class="mb-4">QnA 게시판</h1>
        <b-button variant="dark" size="big">
            <router-link class="text-white" to="/qna/qnaposting">글쓰기</router-link>
        </b-button>
          
        
  
      <hr/>
      <b-table v-if="filteredPosts.length > 0" striped hover :items="filteredPosts" @row-clicked="viewArticle"></b-table>
      <b-table v-else striped hover :items="items" @row-clicked="viewArticle"></b-table>
    
      <b-pagination v-model="currentPage" :total-rows="rows" align="center" pills></b-pagination>
      <b-input-group id="searcharea" class="mb-3">
        <b-form-input v-model="searchQuery" placeholder="내용 검색" />
        <b-input-group-append>
            <b-button @click="search" variant="primary">검색</b-button>
        </b-input-group-append>
    </b-input-group>
    </div>
  </template>
  
  <script>
    import http from "@/util/http-common.js";

  export default {
    data() {
      return {

        rows: 10,
        currentPage: 1,
        searchQuery: "",
  
        items: [
        
        ],
  
        selectedBoard: null,
        filteredPosts: [],
  
      };
    },
    created(){
        http.get("/question").then(({data}) =>{
            this.items = data;
        });
    },
    methods:{
        search(){
            console.log("검색어: ", this.searchQuery);
        },
        viewArticle(item){
        console.log(item);
        this.$router.push(`/qna/detail/${item.questionId}`);
        },  
    }
  };
  </script>
  
  <style scoped>
    #searcharea{
      width: 300px;
      margin: 0 auto;
    }
  </style>