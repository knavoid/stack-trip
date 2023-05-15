<template>
  <div>
   <div id="body">
      <b-jumbotron header="EnjoyTrip">
        <b-carousel
          id="carousel1"
          v-model="slide"
          controls
          indicators
          fade
          img-width="800"
          img-height="300"
          style="text-shadow: 1px 1px 2px #333;"
        >
          <b-carousel-slide
            v-for="(photo, index) in photos"
            :key="index"
            :img-src="photo.src"
            :caption="photo.caption"
          ></b-carousel-slide>
        </b-carousel>
      </b-jumbotron>
    </div>
  </div>
</template>

<script>


export default {
  components: {  },
    name: 'HomeView',
    data() {
    return {
      slide: 0,
      photos: [
        {
          src: 'https://t1.daumcdn.net/cfile/tistory/993E63475C2916510A',
          caption: '',
        },
        {
          src: 'https://via.placeholder.com/1024x480?text=Second+slide',
          caption: 'Second slide',
        },
        {
          src: 'https://via.placeholder.com/1024x480?text=Third+slide',
          caption: 'Third slide',
        },
      ],
    };
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