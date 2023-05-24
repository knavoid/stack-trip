<template>
  <div>
    <b-jumbotron header-level="4">
        <template #header>여행계획 게시판</template>
        <template #lead>
            Stack-Trip과 함께 여행 계획을 세워보세요!
        </template>

        <hr class="my-4" />

        <router-link to="/plan/post"><b-button class="move-btn float-left" pill variant="success" >여행 계획 만들기</b-button></router-link>
    </b-jumbotron>

    <div class="planList">
        <router-link to="/plan/detail">
            <b-table striped hover :items="plans" :fields="plan_fields">
            </b-table>
        </router-link>
    </div>
    

  </div>
</template>

<script>
export default {
    data(){
        return{
            plans: [
                {
                    plan_id: 0,
                    title: "제주도 여행",
                    description: "싸피 방학이니까 여행",
                    begin_date: "2023-06-06",
                    end_date: "2023-06-09",
                }
            ],
            plan_fields: [
                {key: "title", label: "여행 제목"},
                {key: "description", label: "여행 설명"},
                {key: "begin_date", label: "출발일"},
                {key: "end_date", label: "도착일"},

            ]
        };
    },
    method:{
        movePage(){
            console.log("plan 만들기로 이동")
        },
    }
}
</script>

<style scope>

</style>