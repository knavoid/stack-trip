<template>
  <div>
    <div id="login">
      <b-form @submit="onSubmit">
        <b-form-group id="email-input" label="Email address:" label-for="email">
          <b-form-input
            id="email"
            type="email"
            required
            placeholder="E-mail "
            v-model="form.email"
          ></b-form-input>
        </b-form-group>
        <b-form-group id="pw-input" label="비밀번호:" label-for="password">
          <b-form-input
            id="password"
            type="password"
            required
            placeholder="비밀번호"
            v-model="form.password"
          ></b-form-input>
        </b-form-group>

        <b-button type="submit" variant="success">로그인</b-button>
        <!-- Password recovery button -->
        <b-button class="ml-1" variant="warning" @click="openModal"
          >비밀번호 찾기</b-button
        >
      </b-form>

      <hr />
      <p>아직 회원이 아니신가요?</p>
      <h5>지금 가입해보세요!</h5>
      <br />
      <b-button type="submit" variant="primary"
        ><router-link to="/signup">회원가입</router-link></b-button
      >

      <b-modal v-model="showModal" title="비밀번호 찾기">
        <b-form @submit="recoverPassword">
          <b-form-group id="name-input" label="이름:" label-for="name">
            <b-form-input
              id="name"
              type="text"
              required
              placeholder="이름"
              v-model="userName"
            ></b-form-input>
          </b-form-group>
          <b-form-group
            id="email-input-modal"
            label="이메일:"
            label-for="email-modal"
          >
            <b-form-input
              id="email-modal"
              type="email"
              required
              placeholder="E-mail"
              v-model="userEmail"
            ></b-form-input>
          </b-form-group>

          <!-- Submit button within the modal -->
          <b-button type="submit" variant="primary">비밀번호 찾기</b-button>
        </b-form>
      </b-modal>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
export default {
  data() {
    return {
      form: {
        email: "",
        password: "",
      },
      showModal: false,
      userName: "",
      userEmail: "",
    };
  },
  methods: {
    onSubmit(evt) {
      evt.preventDefault();
      console.log(JSON.stringify(this.form));
      if (this.form.email && this.form.password) {
        //로그인 요청 처리 로직
        this.$store
          .dispatch("login", this.form)
          .then(() => {
            this.$router.push("/");
            location.reload();
          })
          .catch((err) => {
            alert(err.response.data.message);
          });
      } else {
        alert("이메일과 비밀번호를 입력해주세요.");
        return false;
      }
    },
    openModal() {
      this.showModal = true;
    },
    recoverPassword(evt) {
      evt.preventDefault();
      console.log(this.userName);
      console.log(this.userEmail);
      if (this.userName && this.userEmail) {
        http
          .get(`/user/password`, {
            params: {
              email: this.userEmail,
              name: this.userName,
            },
          })
          .then((res) => {
            console.log(res);
            alert("당신의 비밀번호는 [ " + res.data + " ] 입니다.");
            this.showModal = false;
          })
          .catch((err) => {
            console.log(err);
            alert("사용자 정보를 찾을 수 없습니다.");
          });
      } else {
        alert("이름과 이메일을 입력해주세요.");
        return false;
      }
    },
  },
};
</script>

<style scope>
#login {
  margin-top: 10%;
  margin-left: 30%;
  margin-right: 30%;
  border: 1px solid #e2e2e2;
  padding: 3%;
}
a {
  color: white;
}
</style>