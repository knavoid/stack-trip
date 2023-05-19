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
        <hr>
        <b-form @submit="onSubmitAnswer">
            <b-form-group class="text-left" label="댓글달기" label-for="content">
            <b-form-textarea
                id="content"
                v-model="newAnswer.content"
                :rows="5"
            ></b-form-textarea>
            </b-form-group>
            <b-button class="" type="submit" variant="primary">댓글달기</b-button>
        </b-form>
        <hr>
        <div>
            <h6 class="text-left">댓글</h6>
            <div v-for="answer in article.answers" :key="answer.answerId">
                        <b-card
                        :header-html="`<span>작성자: ${answer.userName}</span><br/><span>작성시간:${answer.regTime}</span>`"
                        class=" text-left mb-2"
                        border-variant="dark"
                        no-body
                        >
                        <b-card-body class="text-left">
                            <div v-html="answer.content"></div>
                        </b-card-body>
                        
                        <b-button variant="outline-danger" size="sm" @click="DeleteAnswer(answer)">삭제</b-button>
                        </b-card>
            </div>
        </div>
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
                    answers:[],
                },
            newAnswer:{
                questionId: this.$route.params.articleno,
                userCode: 5,
                content: "",
            }
            

        };
    },

    
    created(){
        this.getDetail();
    },
    methods:{
        getDetail(){
            // axios로 해당 board_id에 해당하는 article을 가져온다.
            http.get(`/question/${this.$route.params.articleno}`,{
                headers: {
                    Authorization: `Bearer ${sessionStorage.getItem("token")}`,
                },
            }).then(({ data }) => {
                this.article = data;
                console.log(data);
            });
            // console.log(this.article);
        },
        listArticle(){
            this.$router.push("/qna");
        },
        moveModifyArticle(){
            this.$router.push(`/qna/modify/${this.article.questionId}`);
        },
        deleteArticle(){
            //여기에 article을 삭제하는 함수를 구현한다.
            console.log(`삭제 ${this.$route.params.articleno}`);
            http.delete(`/question/${this.$route.params.articleno}`).then(() => {
                this.$router.push("/qna");
            });
            
        },  
        onSubmitAnswer(evt) {
            evt.preventDefault();
            console.log(this.newAnswer);
            let err = true;
            if (this.newAnswer.content.length >= 1) {
                err = false;
            }
            if (err) {
                alert("내용을 입력해주세요.");
                return;
            } else {
                console.log(this.newAnswer);
                alert("답변이 등록되었습니다.");
                http.post(`/answer`, this.newAnswer)
                .then((response) => {
                    this.newAnswer.content = ""; // 댓글 작성 후 내용 초기화
                    this.newAnswer.userName = response.data.userName; // 작성자 이름 설정
                    this.newAnswer.regTime = response.data.regTime; // 작성 시간 설정
                    this.getDetail(); // 글 상세 정보 다시 가져오기
                })
                .catch((err) => {
                    console.log("error :" + err);
                });
            }
        },
        DeleteAnswer(answer){
            http.delete(`/answer/${answer.answerId}`).then(() => {
                this.getDetail();
            });
        }
    }
}
</script>

<style>

</style>