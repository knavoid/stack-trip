<template>
    <div class="container">
        <h1 class="mt-5">QnA 수정</h1>
        <b-form @submit="onSubmit">
        <b-form-group label="내용" label-for="content">
            <b-form-textarea
            id="content"
            v-model="form.content"
            :rows="5"
            ></b-form-textarea>
        </b-form-group>
        <b-button class="mb-5" type="submit" variant="primary">수정하기</b-button>
        </b-form>
    </div>
</template>

<script>
import http from "@/util/http-common.js";
export default {
    data() {
        return {  
        form: {
            answers: [],
            content: "",
            questionId: this.$route.params.articleno,
            userCode: 5,
            userName: "",
        },
        
        
        };
    },
    created(){
        console.log(this.$route.params.articleno);
        this.getArticle();
    },
    methods: {
        getArticle(){
            http.get(`/question/${this.$route.params.articleno}`).then(({ data }) => {
                this.form = data;
                console.log(data);
            });
        },
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
            http.put(`/question/${this.$route.params.articleno}`, this.form).then(() => {
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