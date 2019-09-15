<template>
  <div class="con">
    <el-steps :active="active" finish-status="success">
      <el-step title="步骤 1">
      </el-step>
      <el-step title="步骤 2">
      </el-step>
      <el-step title="步骤 3"></el-step>
    </el-steps>

    <div v-if="active==0">
      <h1>选择你的照片</h1>
      <ul class="nav nav-tabs">
        <li class="active">
          <a data-toggle="tab" href="#" class="tab" aria-expanded="true">Upload photo</a>
        </li>
      </ul>
      <div class="tab-content">

        <div class="tab-pane active">
          <form method="post" class="dropzone  dz-clickable" @click="content_click">
            <input type="file" accept="image/*" style="display: none" ref="content_ref" multiple="multiple" @change="get_content($event)">
            <div v-if="img1.length<2" class="dz-message">
              <p>拖拽你喜爱 <span class="c-homepage__slogan-heart"></span>图片进入 或者<br>点击从本地资源选取图片</p>
            </div>
            <img v-else :src="img1" class="img-responsive big">
          </form>
        </div>
      </div>
    </div>

    <div v-if="active==1">
      <h1>选择你的样式</h1>
      <div class="">
        <div class="row">
          <div class="col-lg-6 top_to">
            <ul class="nav nav-tabs">
              <li class="active">
                <a data-toggle="tab" href="#" class="tab" aria-expanded="true">Upload style</a>
              </li>
            </ul>
            <div class="mail_container" @click="style_click1">
              <input type="file" accept="image/*" style="display: none" ref="style_ref1" multiple="multiple" @change="get_style1($event)">
              <div v-if="style1.length<2" class="no_img">
                <img src="../../assets/jia.png" class="img-responsive">
              </div>
              <div v-else class="has_img">
                <img :src="style1" class="big">
              </div>
            </div>
          </div>
          <div class="col-lg-6 top_to">
            <ul class="nav nav-tabs">
              <li class="active">
                <a data-toggle="tab" href="#" class="tab" aria-expanded="true">Upload style</a>
              </li>
            </ul>
            <div class="mail_container" @click="style_click2">
              <input type="file" accept="image/*" style="display: none" ref="style_ref2" multiple="multiple" @change="get_style2($event)">
              <div v-if="style2.length<2" class="no_img">
                <img src="../../assets/jia.png" class="img-responsive">
              </div>
              <div v-else class="has_img">
                <img :src="style2" class="big">
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="">
        <div class="row">
          <div class="col-lg-6 top_to">
            <ul class="nav nav-tabs">
              <li class="active">
                <a data-toggle="tab" href="#" class="tab" aria-expanded="true">Upload style</a>
              </li>
            </ul>
            <div class="mail_container" @click="style_click3">
              <input type="file" accept="image/*" style="display: none" ref="style_ref3" multiple="multiple" @change="get_style3($event)">
              <div v-if="style3.length<2" class="no_img">
                <img src="../../assets/jia.png" class="img-responsive">
              </div>
              <div v-else class="has_img">
                <img :src="style3" class="big">
              </div>
            </div>
          </div>
          <div class="col-lg-6 top_to">
            <ul class="nav nav-tabs">
              <li class="active">
                <a data-toggle="tab" href="#" class="tab" aria-expanded="true">Upload style</a>
              </li>
            </ul>
            <div class="mail_container" @click="style_click4">
              <input type="file" accept="image/*" style="display: none" ref="style_ref4" multiple="multiple" @change="get_style4($event)">
              <div v-if="style4.length<2" class="no_img">
                <img src="../../assets/jia.png" class="img-responsive">
              </div>
              <div v-else class="has_img">
                <img :src="style4" class="big">
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div v-if="active==2">
      <div class="row" style="margin-top: 100px">
        <div class="col-lg-6">
          <div class="sub-title">选择图片分辨率</div>
          <el-autocomplete
            class="inline-input"
            v-model="content_size"
            :fetch-suggestions="querySearch"
            placeholder="如:256"
            oninput = "value=value.replace(/[^\d]/g,'')"
          ></el-autocomplete>
        </div>
        <div class="col-lg-6">
          <div class="sub-title">是否保留内容图片颜色</div>
          <el-select v-model="color" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </div>
      </div>
      <div class="row" style="margin-top: 20px">
        <div class="col-lg-6">
        <div class="sub-title">样式化程度(1-10):样式1</div>
        <el-input-number v-model="num1" :step="1"></el-input-number>
      </div>
        <div class="col-lg-6">
          <div class="sub-title">样式化程度(1-10):样式2</div>
          <el-input-number v-model="num2" :step="1"></el-input-number>
        </div>
        <div class="col-lg-6">
          <div class="sub-title">样式化程度(1-10):样式3</div>
          <el-input-number v-model="num3" :step="1"></el-input-number>
        </div>
        <div class="col-lg-6">
          <div class="sub-title">样式化程度(1-10):样式4</div>
          <el-input-number v-model="num4" :step="1"></el-input-number>
        </div>
      </div>
      <div class="row" style="margin-top: 50px;display: flex">
        <div class="col-lg-4 mail_title">e-mail</div>
        <div class="col-lg-8 form-group">
          <input class="pull-left form-control e_input" v-model="e_mail" type="email" placeholder="请输入邮箱"></input>
        </div>
      </div>
    </div>

    <div v-if="active==3">
      <h1>完成</h1>
      <p>请注意查收邮箱</p>
    </div>
    <el-button style="margin-top: 12px;" @click="next">下一步</el-button>


  </div>
</template>

<script>
    export default {
        name: "muilt",
        data() {
            return {
                restaurants: [],
                active: 0,
                img1:'',
                file1:null,

                style1:'',
                style_file1:null,

                style2:'',
                style_file2:null,

                style3:'',
                style_file3:null,

                style4:'',
                style_file4:null,

                content_size: '',
                style_size: '',
                color:'',
                options: [ {
                    value: 'true',
                    label: '保留'
                }, {
                    value: 'false',
                    label: '不保留'
                }],
                num1:0,
                num2:0,
                num3:0,
                num4:0,
                e_mail:'',

            };
        },
        mounted(){
           this.init_data()
            this.restaurants = this.loadAll();
        },

        methods: {
            init_data(){
                this.img1='',
                    this.file1=null,

                    this.style1='',
                    this.style_file1=null,

                    this.style2='',
                    this.style_file2=null,

                    this.style3='',
                    this.style_file3=null,

                    this.style4='',
                    this.style_file4=null,

                    this.content_size= '',
                    this.style_size= '',
                    this.color='',
                    this.num1=0,
                    this.num2=0,
                    this.num3=0,
                    this.num4=0,
                    this.e_mail=''
            },
            next() {
              if(this.active==3){
                  this.active=0
                  this.init_data()
                  return
              }
              switch (this.active) {
                  case 0:{
                      if(this.file1==null){
                          alert("请选择照片")
                      }else
                          this.active++
                      break
                  }
                  case 1:{
                      if(this.style_file1==null&&this.style_file2==null&&this.style_file3==null&&this.style_file4==null){
                          alert("请至少上传一种样式")
                      }else
                          this.active++
                      break
                  }
                  case 2:{
                      if(this.check_data()){
                          this.sub_change()
                          this.active++
                      }
                      break
                  }
              }

            },
            getImgURL(file) {
                var url = null;
                if(window.createObjectURL != undefined) { // basic
                    url = window.createObjectURL(file);
                } else if(window.URL != undefined) { // mozilla(firefox)
                    url = window.URL.createObjectURL(file);
                } else if(window.webkitURL != undefined) { // web_kit or chrome
                    url = window.webkitURL.createObjectURL(file);
                }
                return url;
            },

            content_click(){
                this.$refs.content_ref.click()
            },
            get_content(event){
                this.file1 = event.target.files[0]; //获取input的图片file值
                this.img1=this.getImgURL(this.file1)
            },

            style_click1(){
                this.$refs.style_ref1.click()
            },
            get_style1(event){
                this.style_file1= event.target.files[0];
                this.style1=this.getImgURL(this.style_file1)
            },

            style_click2(){
                this.$refs.style_ref2.click()
            },
            get_style2(event){
                this.style_file2= event.target.files[0];
                this.style2=this.getImgURL(this.style_file2)
            },

            style_click3(){
                this.$refs.style_ref3.click()
            },
            get_style3(event){
                this.style_file3= event.target.files[0];
                this.style3=this.getImgURL(this.style_file3)
            },

            style_click4(){
                this.$refs.style_ref4.click()
            },
            get_style4(event){
                this.style_file4= event.target.files[0];
                this.style4=this.getImgURL(this.style_file4)
            },

            querySearch(queryString, cb) {
                var restaurants = this.restaurants;
                var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
                // 调用 callback 返回建议列表的数据
                cb(results);
            },
            createFilter(queryString) {
                return (restaurant) => {
                    return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
                };
            },
            loadAll() {
                return [
                    { "value": "低品质", "address": "64" },
                    { "value": "标准品质", "address": "256" },
                    { "value": "高品质", "address": "512" }
                ];
            },


            check_data(){
                if(this.content_size=='低品质'||this.content_size<128) {
                    this.content_size = '64'
                }
                else if(this.content_size=='标准品质'||(this.content_size>=128&&this.content_size<512)){
                    this.content_size='256'
                }else{
                    this.content_size='512'
                }

                this.style_size==this.content_size

                if(this.color==''){
                    alert("请选择是否保留图片背景颜色")
                    return false
                }
                if(this.num1<0||this.num1>10||this.num2<0||this.num2>10||this.num3<0||this.num3>10||this.num4<0||this.num4>10){
                    alert("请为每一种样式设置样式化程度（0-10）")
                    return false
                }
                var re=/^\w+@[a-z0-9]+\.[a-z]+$/i
                if (!re.test(this.e_mail)){
                    alert("请输入正确邮箱格式")
                    return false
                }
                return true

            },
            sub_change(){
                var formfile=new FormData()
                formfile.append("image",this.file1)
                formfile.append("style1",this.style_file1)
                formfile.append("style2",this.style_file2)
                formfile.append("style3",this.style_file3)
                formfile.append("style4",this.style_file4)

                var obj={
                    content_size:this.content_size,
                    style_size:this.style_size,
                    color:this.color,
                    num1:this.num1,
                    num2:this.num2,
                    num3:this.num3,
                    num4:this.num4,
                    email:this.e_mail
                }
                formfile.append("obj",JSON.stringify(obj))
                alert("上传成功，请注意查收邮箱")
                this.axios({
                    method:'post',
                    url:'/api/change_muilt',
                    data: formfile,
                }).then((res)=>{
                    console.log(res)
                    // if(res.data.state=='success')
                    //     alert("上传成功,注意查收邮箱")
                    //相应后上传样式
                })
            },
        }
    }
</script>

<style scoped>
  .con{
    font-family: "Microsoft YaHei", "宋体", "Helvetica Neue", Helvetica, Arial, sans-serif;
    font-weight: 500;
  }

  .dropzone {
    min-height: 150px;
    border: 2px dashed #9f9f9f;
    border-radius: 5px;
    background: white;
    padding: 20px 20px;
  }
  .dropzone.dz-clickable {
    cursor: pointer;
  }
  .dropzone .dz-message {
    text-align: center;
    margin: 2em 0;
    font-family: Georgia,"Times New Roman",Times,serif;
    font-size: 20px;
    font-style: italic;
  }
  .mail_title{
    text-align: center;
    font-family: Georgia,"Times New Roman",Times,serif;
    font-size: 30px;
    font-style: italic;
    margin-left: 10%;
  }
  .big{
    max-width: 200px;
    max-height: 200px;
  }
  .mail_container{
    min-height: 100px;
    border: 2px dashed #9f9f9f;
    border-radius: 5px;
    background: white;
    padding: 20px 20px;
  }
  .no_img img{
   margin-left: 36%;
  }
  .top_to{
    margin-top: 20px;
  }
  .sub-title{
    margin-top: 20px;
    margin-bottom: 20px;
    font-family: "Microsoft YaHei", "宋体", "Helvetica Neue", Helvetica, Arial, sans-serif;
  }
  .e_input{
    width: 90%;
  }
  .c-homepage__slogan-heart {
    display: inline-block;
    background: url(../../assets/icon-heart.svg) no-repeat center;
    background-size: 100%;
    width: 24px;
    height: 20px;
    font-family: "Microsoft YaHei", "宋体", "Helvetica Neue", Helvetica, Arial, sans-serif;
  }
</style>
