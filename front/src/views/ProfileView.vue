<template>
    <b-container>
        <h1>마이 페이지</h1>
        <b-form @submit="modifyUserInfo">
            <b-form-group label="이메일" label-for="email">
                <b-form-input id="email" v-model="user.email" type="email" required readonly></b-form-input>
            </b-form-group>
            <b-form-group label="이름" label-for="name">
                <b-form-input id="name" v-model="user.name" type="text" required ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호" label-for="password">
                <b-form-input id="password" v-model="user.password" type="text" required ></b-form-input>
            </b-form-group>
            <b-button type="submit" variant="primary">수정</b-button>
            <b-button class="ml-3" variant="danger" @click="deleteUser">회원탈퇴</b-button>
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
        
        modifyUserInfo(){
            http.put(`/user/${this.user.userCode}`, this.user)
            .then(
                ({data}) => {
                    console.log(data);
                    alert('수정되었습니다.')
                    // this.$router.push('/profile')
                    window.location.reload()
                }
            ).catch(
                error => {
                    console.log(error)
                }
            )
        },
        deleteUser(){
            console.log(this.user.userCode)
            http.delete(`/user/${this.user.userCode}`)
            .then(
                ({data}) => {
                    console.log(data);
                    alert('탈퇴되었습니다.')
                    sessionStorage.removeItem('token')
                    window.location.reload()
                    this.$router.push('/')
                }
            ).catch(
                error => {
                    console.log(error)
                }
            )
        }
    }
}
</script>

<style scoped>

</style>