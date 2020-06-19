<template>
  <div>
    <common-header></common-header>
    <div class="main">
      <mt-field label="自我介绍" placeholder="短信内容" type="textarea" rows="4" v-model="msg"></mt-field>
      <mt-field label="选择excel" >
          <input type="file" id="excelInputFile" v-on:change="fileChange()"/>
      </mt-field>
      <mt-field label="电话列名" placeholder="请输入电话号码所在的列名" v-model="columnName"></mt-field>
      <mt-field>
          <mt-button @click="buildMsg" size="small" type="primary">生成链接</mt-button>
      </mt-field>
      <mt-field>
          <mt-button @click="getLink" size="small" type="primary">获取链接</mt-button>
      </mt-field>
      <mt-field>
          <mt-button @click="saveLink" size="small" :disabled="!ready" type="primary">保存链接</mt-button>
      </mt-field>
      <mt-field>
          <mt-button @click="sendMsg" size="small" type="primary" :disabled="!ready">发送短信</mt-button>
      </mt-field>
      <a :href="telhref" ref="tela" style="visibility:hidden;" ></a>
    </div>
  </div>
</template>
<script>
import XLSX from "xlsx";
import CommonHeader from "@/components/main/CommonHeader";
import { saveLink,getLink} from '@/api/phone-message'
export default {
  name: "SendMessages",
  components: {
    "common-header": CommonHeader
  },
  data() {
    return {
      msg: "",
      columnName: "",
      filePath: "",
      telhref: "",
      ready: false,
      phones: ""
    };
  },
  methods: {
    fileChange() {
      this.filepath = document.getElementById("excelInputFile").value;
    },
    buildMsg() {
      this.ready = false;
      if (this.filepath == "" || this.msg == "" || this.columnName == "") {
        this.$toast({
          message: "数据不全",
          position: "top",
          duration: 2000
        });
        return;
      }
      var that = this;
      var loading = this.$loading({
        lock: true,
        text: "构建链接中",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)"
      });
      this.readWorkbookFromLocalFile(
        document.getElementById("excelInputFile").files[0],
        function(workbook) {
          var sheetNames = workbook.SheetNames; // 工作表名称集合
          var worksheet = workbook.Sheets[sheetNames[0]]; // 这里我们只读取第一张sheet
          var datajson = XLSX.utils.sheet_to_json(worksheet);
          var myList = new Array();
          for (var i = 0; i < datajson.length; i++) {
            myList.push(datajson[i][that.columnName]);
          }
          that.phones = myList.join(",");
          that.buildHref();
          loading.close();
          that.ready = true;
        }
      );
    },
    sendMsg() {
      this.$refs.tela.click();
    },
    buildHref() {
      this.telhref = "sms://" + this.phones + "?body=" + this.msg;
    },
    readWorkbookFromLocalFile(file, callback) {
      var reader = new FileReader();
      reader.onload = function(e) {
        var data = e.target.result;
        var workbook = XLSX.read(data, { type: "binary" });
        if (callback) callback(workbook);
      };
      reader.readAsBinaryString(file);
    },
    saveLink() {
        saveLink({phones:this.phones, body:this.msg}).then(()=>{
            this.$toast('保存成功')
        }).catch(()=>{
            this.$toast('保存失败')
        })
    },
    getLink() {
        getLink().then((res)=>{
            this.phones=res.data.phones;
            this.msg=res.data.body;
            this.$toast('获取成功');
        }).catch(()=>{
            this.$toast('获取失败');
        })
    }
  }
};
</script>
<style scoped>
.main {
  margin-top: 40px;
}
</style>