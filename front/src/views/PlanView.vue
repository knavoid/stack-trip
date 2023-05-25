<template>
  <div>
    <b-jumbotron header-level="4">
      <template #header>TRIP PLAN</template>
      <template #lead> Stack-Trip과 함께 여행 계획을 세워보세요! </template>

      <hr class="my-4" />
      <b-form @submit="onSubmit">
        <b-form-group label="여행 제목을 입력하세요" label-for="title-input">
          <b-form-input
            id="title-input"
            class="mb-3"
            v-model="newTrip.title"
            placeholder="여행 제목을 입력하세요"
          ></b-form-input>
        </b-form-group>
        <b-row class="pr-3 pl-3 mb-3">
          <b-col class="pl-0 pr-1">
            <label for="start-datepicker">시작 날짜</label>
            <b-form-datepicker
              id="start-datepicker"
              v-model="newTrip.beginDate"
              class="mb-2"
            ></b-form-datepicker>
          </b-col>
          <b-col class="pl-1 pr-0">
            <label for="end-datepicker">종료 날짜</label>
            <b-form-datepicker
              id="end-datepicker"
              v-model="newTrip.endDate"
              class="mb-2"
            ></b-form-datepicker>
          </b-col>
          <!-- textarea로 추후 수정  -->
        </b-row>
        <b-form-group
          label="여행 설명을 입력하세요"
          label-for="description-input"
        >
          <b-form-input
            id="description-input"
            class="mt-1"
            v-model="newTrip.description"
            placeholder="여행 정보를 입력하세요"
          ></b-form-input>
        </b-form-group>
        <b-button type="submit" class="move-btn mt-3" pill variant="success"
          >여행 계획 만들기</b-button
        >
      </b-form>
    </b-jumbotron>

    <div class="planList">
      <b-table
        striped
        hover
        :items="plans"
        :fields="plan_fields"
        @row-clicked="moveToPosting"
      ></b-table>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
export default {
  data() {
    return {
      userCode: null,
      newTrip: {
        title: "",
        description: "",
        beginDate: "",
        endDate: "",
      },

      // 얘는 백엔드에서 받아올 PlanList
      plans: [],
      plan_fields: [
        { key: "title", label: "여행 제목" },
        { key: "description", label: "여행 설명" },
        { key: "beginDate", label: "출발일" },
        { key: "endDate", label: "도착일" },
      ],
    };
  },
  created() {
    this.getPlanList();
  },
  methods: {
    onSubmit(evt) {
      evt.preventDefault();
      // 여행 계획 만들기 기능을 구현하고자 하는 경우에는 해당 부분을 추가로 작성하세요.
      console.log(this.newTrip);
      this.newTrip.userCode = this.userCode;
      console.log(JSON.stringify(this.newTrip));
      http.post(`/trip`, JSON.stringify(this.newTrip)).then(({ data }) => {
        console.log(data);
        // this.$router.push(`/plan/post/${data.tripId}`);
        window.location.reload();
      });
    },
    getPlanList() {
      http
        .get(`/user`, {
          headers: {
            Authorization: `Bearer ${sessionStorage.getItem("token")}`,
          },
        })
        .then(({ data }) => {
          this.userCode = data.userCode;
          http
            .get(`/trip`, {
              params: {
                userCode: this.userCode,
              },
            })
            .then(({ data }) => {
              console.log(data);
              this.plans = data;
            })
            .catch((error) => {
              console.log("여행 계획 로드 실패" + error);
            });
        })
        .catch((error) => {
          alert("로그인이 필요한 서비스입니다.");
          this.$router.push("/login");
          console.log("유저정보 로드 실패" + error);
        });
    },
    moveToPosting(item) {
      console.log(item);
      this.$router.push(`/plan/post/${item.tripId}`);
    },
  },
};
</script>

<style scoped>
label {
  font-size: 1.2rem;
  font-weight: bold;
}
</style>
  