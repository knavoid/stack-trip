<template>
    <div>
        <div id="signup">
            <b-form>
                <b-form-group id="email-input" label="Email address:" label-for="email">
                    <b-form-input id="email" type="email" v-model="user.email" required placeholder="E-mail"></b-form-input>
                    <b-button class="btn mt-3" type="button" variant="primary" @click="emailCheck">중복확인</b-button>
                </b-form-group>
                
                <b-form-group id="name-input" label="이름:" label-for="name">
                    <b-form-input id="name" type="text" v-model="user.name" required placeholder="이름"></b-form-input>
                </b-form-group>

                <b-form-group id="pw-input" label="비밀번호:" label-for="password">
                    <b-form-input id="password" type="password" v-model="user.password" required placeholder="비밀번호 " @keyup="checkpwd()"></b-form-input>
                </b-form-group>

                <b-form-group id="pw-check" label="비밀번호 확인:" label-for="passwordCheck">
                    <b-form-input id="passwordCheck" type="password" required placeholder="비밀번호 확인" @keyup="checkpwd()"></b-form-input>
                    <span id="checkResult"></span>
                </b-form-group>

                <b-button class="btn" type="submit" variant="primary" @click="signup">회원가입</b-button>
                <b-button class="btn" type="reset" variant="danger">취소</b-button>


            </b-form>
        </div>
    </div>
</template>

<script>
import http from '@/util/http-common.js'
export default {
    name: 'SignupView',
    components: {
    },
    data() {
    return {
        user:{
            email: '',
            name: '',
            password: '',
        },
        isPossible: false,
    }
    },
    methods: {
            emailCheck(){
                console.log(this.user.email);
                http.get(`/user/email?email=${this.user.email}`)
                    .then((response) => {
                        if(response.data == false){
                            console.log(response.data);
                            alert("사용 가능한 이메일입니다.")
                            this.isPossible = true
                        }else{
                            console.log(response.data)
                            alert("이미 사용중인 이메일입니다.")
                            this.isPossible = false
                        }
                    })
            },
            signup(event){
                event.preventDefault();
                if(this.isPossible == true){
                    http.post('/user/register', this.user)
                        .then((response) => {
                            alert(`${response.data.name}님 회원가입을 축하드립니다.`);
                            this.$router.push('/login');
                        })
                        .catch((error) => {
                            alert("회원가입에 실패하였습니다.");
                            console.log(error);
                        })
                }else{
                    alert("이메일 중복확인을 해주세요.")
                }
            },
            checkpwd(){
                if(document.getElementById("password").value != ''){
                    if(document.getElementById("passwordCheck").value != ''){
                        if(document.getElementById("password").value == document.getElementById("passwordCheck").value){
                            document.getElementById("checkResult").innerHTML = "비밀번호가 일치합니다.";
                            document.getElementById("checkResult").style.color = "blue";
                        }else{
                            document.getElementById("checkResult").innerHTML = "비밀번호가 일치하지 않습니다.";
                            document.getElementById("checkResult").style.color = "red";
                        }
                }else{
                    document.getElementById("checkResult").innerHTML = "";
                }
            }
            }
        }
    }
</script>
    
<style scope>
    #signup {
        margin-top: 10%;
        margin-left: 30%;
        margin-right: 30%;
        border: 1px solid #e2e2e2;
        padding: 3%;
    }
    .btn {
        margin-right: 1%;
    }

</style>
