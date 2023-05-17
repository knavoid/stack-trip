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
                :header-html="`<h3>${article.articleno}.
                ${article.subject} [${article.hit}]</h3><div><h6>${article.userid}</div><div>${article.regtime}</h6></div>`"
                class="mb-2"
                border-variant="dark"
                no-body
                >
                <b-card-body class="text-left">
                    <div v-html="article.message"></div>
                </b-card-body>
                </b-card>
            </b-col>
        </b-row>
        
    </b-container>
</template>

<script>
export default {
    name: "BoardDetail",
    data(){
        return{
            article: 
                {
                    articleno: 1,
                    subject: "필터테스트 1",
                    userid: "Macdonald",
                    regtime: "23-05-15 09:32",
                    hit: 3,
                    message: "필터테스트 1어쩌구저쩌구 질문글 어쩌구",
                },
          
            

        };
    },
    computed(){
        if(this.article.content)
            return this.article.content.split("\n").join("<br/>");
        return "";
    },
    
    created(){
        // axios로 해당 board_id에 해당하는 article을 가져온다.

    },
    methods:{
        
        listArticle(){
            this.$router.push("/board");
        },
        moveModifyArticle(){
            this.$router.push("/board/modify");
        },
        deleteArticle(){
            //여기에 article을 삭제하는 함수를 구현한다.
        },  
    }
}
</script>

<style>

</style>