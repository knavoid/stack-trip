<template>
  <div>
   <div id="body">
      <div>
        <b-jumbotron class="jumbotron-with-background" text-variant="white">
          <template #header><span>Stack</span>Trip</template>

          <template #lead>
            지금 <span>StackTrip</span>과 함께 <br/>우리 지역의 관광지를 알아보고 여행을 떠나보세요!
          </template>

          <hr class="my-4">


          <!-- <router-link to="/signup"><b-button class="mr-3" variant="info">회원가입 하러가기</b-button></router-link> -->
          <router-link to="/trip"><b-button class="ml-3" variant="info">여행지 탐색하기</b-button></router-link>
        </b-jumbotron>
      </div>

      <b-row>
        <b-col cols="6" id="top-five">
            <h3>TOP5 관광지</h3>
            <b-table :items="top_attraction" :fields="attraction_fields">
              <template #cell(image)="row">
                <img :src="row.item.image" :alt="`사진`" width="100" height="100" />
              </template>
              <template #cell(title)="row">
                {{ row.item.title }}
              </template>
              <template #cell(address)="row">
                {{ row.item.address }}
              </template>
            </b-table>
        </b-col>
        <b-col cols="6">
          <h3>HOT 게시글</h3>
          <b-table :items="top_post" :fields="post_fields">
            <template #ceil(title)="row">
              {{ row.item.subject }}
            </template>
            <template #ceil(userName)="row">
              {{ row.item.userName }}
            </template>
            <template #ceil(regTime)="row">
              {{ row.item.regTime }}
            </template>
            <template #ceil(views)="row">
              {{ row.item.views }}
            </template>
          </b-table>
        </b-col>
      </b-row>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common.js";

export default {
    name: 'HomeView',
    data() {
    return {
        attraction_fields:[
          { key: 'image', label: '사진' },
          { key: 'title', label: '관광지명' },
          { key: 'address', label: '주소'}
          ],
        post_fields:[
          { key: 'subject', label: '제목'},
          { key: 'userName', label: '작성자'},
          { key: 'regTime', label: '작성일'},
          { key: 'views', label: '조회수'}
        ],
        top_attraction:[

        ],
        top_post:[

        ]
    };
  },
  created() {
    this.getTopAttraction();
    this.getTopPost();
  },
  methods: {
    getTopAttraction() {
      http.get(`attraction/top5`).then((response) => {
        this.top_attraction = response.data;
        console.log(response.data);
      });
    },
    getTopPost(){
      http.get(`post/list/top5`)
        .then((response) => {
          this.top_post = response.data;
        })
    }
  },
};
</script>

<style scoped>
  .jumbotron-with-background {
    padding-top: 40px;
    background-image: url('@/assets/img/trippic1.jpg');
    background-size: cover;
    background-position: center;
    text-align: left;
  }
  .jumbotron-with-background span {
    color: #4ebb6d;
  }
  a {
    text-decoration: none;
  }
</style>