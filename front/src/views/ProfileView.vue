<template>
    <b-container>
        <h1>마이 페이지</h1>
        <b-form>
            <b-form-group label="이메일" label-for="email">
                <b-form-input id="email" v-model="user.email" type="email" required readonly></b-form-input>
            </b-form-group>
            <b-form-group label="이름" label-for="name">
                <b-form-input id="name" v-model="user.name" type="text" required readonly></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호" label-for="password">
                <b-form-input id="password" v-model="user.password" type="text" required readonly></b-form-input>
            </b-form-group>
            <b-button type="submit" variant="primary">수정</b-button>
        </b-form>
    </b-container>
</template>

<script>
import http from '@/util/http-common.js'

export default {
    data(){
        return{
            user: {
                userCode: 0,
                email:"dummy@test.com",
                password: "test1234",
                name: "testUser1",
            }
        }
    },
    mounted() {
        this.getUserInfo();
    },
    methods: {
        getUserInfo(){
            const token = sessionStorage.getItem('token')
            http.get('/user', {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }).then(
            ({data}) => {
                this.user = data
            }
        ).catch(
            error => {
                console.log(error)
            }
        )
        },
        
        updatePassword(){

            alert("비밀번호 변경");
        }
    }
}
</script>

<style scoped>

</style>