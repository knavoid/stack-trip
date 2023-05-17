<template>
    <b-container class="bv-example-row mt-3">
        <b-row>
        <b-col>
            <b-alert show><h3>글보기</h3></b-alert>
        </b-col>
        </b-row>
        <b-row class="mb-1">
        <b-col class="text-left">
            <b-button variant="outline-primary" @click="listArticle">목록</b-button>
        </b-col>
        <b-col class="text-right">
            <b-button
            variant="outline-info"
            size="sm"
            @click="moveModifyArticle"
            class="mr-2"
            >글수정</b-button
            >
            <b-button variant="outline-danger" size="sm" @click="deleteArticle"
            >글삭제</b-button
            >
        </b-col>
        </b-row>
        <b-row class="mb-1">
            <b-col>
                <b-card
                :header-html="`<h3>${article.questionId}번 질문글
                </h3><div><h6>사용자 코드: ${article.userCode}</div><div>작성시간: ${article.regTime}</h6></div>`"
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
        
    </b-container>
</template>

<script>
import http from "@/util/http-common.js";
export default {
    name: "BoardDetail",
    data(){
        return{
            article: 
                {
                    questionId: 0,
                    userCode: 0,
                    content: "",
                    regTime: "",
                    
                },
            answer_article:[]
            

        };
    },

    
    created(){
        // axios로 해당 board_id에 해당하는 article을 가져온다.
        http.get(`/question/${this.$route.params.articleno}`).then(({ data }) => {
            this.article = data;
            console.log(data);
        });
        // console.log(this.article);

        

    },
    methods:{
        
        listArticle(){
            this.$router.push("/qna");
        },
        moveModifyArticle(){
            this.$router.push(`/board/modify/${this.article.questionId}`);
        },
        deleteArticle(){
            //여기에 article을 삭제하는 함수를 구현한다.
            console.log(`삭제 ${this.$route.params.articleno}`);
            http.delete(`/question/${this.$route.params.articleno}`).then(() => {
                this.$router.push("/qna");
            });
            
        },  
    }
}
</script>

<style>

</style>