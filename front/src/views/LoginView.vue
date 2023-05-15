<template>
    <div>
        <div id="login">
            <b-form>
                <b-form-group id="email-input" label="Email address:" label-for="email">
                    <b-form-input id="email" type="email" required placeholder="E-mail "></b-form-input>
                </b-form-group>
                <b-form-group id="pw-input" label="비밀번호:" label-for="password">
                    <b-form-input id="password" type="password" required placeholder="비밀번호 "></b-form-input>
                </b-form-group>

                <b-button type="submit" variant="success">로그인</b-button>

                <hr>
                <p>아직 회원이 아니신가요?</p>
                <h5>지금 가입해보세요!</h5>
                <br>
                <b-btn type="submit" variant="primary"><router-link to="/signup">회원가입</router-link></b-btn>


            </b-form>
        </div>
    </div>

</template>

<script>

export default {
  components: {  },

}
</script>

<style scope>
    #login {
        margin-top: 10%;
        margin-left: 30%;
        margin-right: 30%;
        border: 1px solid #e2e2e2;
        padding: 3%;
    }
    a{
        color: white;
    }
    
</style>