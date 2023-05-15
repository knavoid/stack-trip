<template>
  <div>
    <div id="signup">
            <b-form>
                <b-form-group id="email-input" label="Email address:" label-for="email">
                    <b-form-input id="email" type="email" required placeholder="E-mail"></b-form-input>
                </b-form-group>
                
                <b-form-group id="name-input" label="이름:" label-for="name">
                    <b-form-input id="name" type="text" required placeholder="이름"></b-form-input>
                </b-form-group>

                <b-form-group id="pw-input" label="비밀번호:" label-for="password">
                    <b-form-input id="password" type="password" required placeholder="비밀번호 "></b-form-input>
                </b-form-group>

                <b-form-group id="pw-check" label="비밀번호 확인:" label-for="passwordCheck">
                    <b-form-input id="passwordCheck" type="password" required placeholder="비밀번호 확인"></b-form-input>
                </b-form-group>

                <b-button class="btn" type="submit" variant="primary">회원가입</b-button>
                <b-button class="btn" type="reset" variant="danger">취소</b-button>


            </b-form>
        </div>
  </div>
</template>

<script>
export default {

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