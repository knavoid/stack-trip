<template>
  <div>
   <div id="body">
      <b-jumbotron>
        <template #header>BootstrapVue</template>

        <template #lead>
          This is a simple hero unit, a simple jumbotron-style component for calling extra attention to
          featured content or information.
        </template>

        <hr class="my-4">

        <p>
          It uses utility classes for typography and spacing to space content out within the larger
          container.
        </p>

        <b-button variant="primary" href="#">Do Something</b-button>
        <b-button variant="success" href="#">Do Something Else</b-button>
      </b-jumbotron>

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
#body {
  margin-right: 5%;
  margin-left: 5%;
}
@media(max-width: 768px) {
  #body {
    margin-right: 0%;
    margin-left: 0%;
  }
}
</style>