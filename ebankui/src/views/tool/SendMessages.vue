<template>
  <div>
    <common-header></common-header>
    <div class="main">
      <mt-field label="选择模板" v-model="modelName" :readonly="true" :disableClear="true">
        <mt-button @click="sheetVisible=true" size="small" type="primary">选择</mt-button>
      </mt-field>
      <mt-field label="短信内容" placeholder="请输入短信内容" type="textarea" rows="4" v-model="msg"></mt-field>
      <mt-field label="选择excel">
        <input type="file" id="excelInputFile" v-on:change="fileChange()" />
      </mt-field>
      <mt-field label="电话列名" placeholder="请输入电话号码所在的列名" v-model="columnName"></mt-field>
      <div class="action-button-container">
        <div class="action-button-wrapper">
          <mt-button @click="buildMsg" size="small" type="primary" class="action-button">生成链接</mt-button>
        </div>
      </div>
      <div class="action-button-container">
        <div class="action-button-wrapper">
          <mt-button @click="getLink" size="small" type="primary" class="action-button">获取链接</mt-button>
        </div>
      </div>
      <div class="action-button-container">
        <div class="action-button-wrapper">
          <mt-button
            @click="saveLink"
            size="small"
            :disabled="!ready"
            type="primary"
            class="action-button"
          >保存链接</mt-button>
        </div>
      </div>
      <div class="action-button-container">
        <div class="action-button-wrapper">
          <mt-button
            @click="sendMsg"
            size="small"
            type="danger"
            :disabled="!ready"
            class="action-button"
          >发送短信</mt-button>
        </div>
      </div>
      <a :href="telhref" ref="tela" style="visibility:hidden;"></a>
      <mt-actionsheet :actions="actions" v-model="sheetVisible"></mt-actionsheet>
      <div style="    height: 100%;background: white;width: 100%;position: absolute;"></div>
    </div>
  </div>
</template>
<script>
import XLSX from "xlsx";
import CommonHeader from "@/components/main/CommonHeader";
import { saveLink, getLink, getMessageModels } from "@/api/phone-message";
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
      phones: "",
      sheetVisible: false,
      actions: [],
      modelName: "自定义"
    };
  },
  beforeMount() {
    getMessageModels().then(res => {
      var data = res.body;
      console.log(data);
      this.actions.push({
        name: "自定义",
        method: this.onModelChange,
        content: ""
      });
      for (var i = 0; i < data.length; i++) {
        this.actions.push({
          name: data[i].name,
          method: this.onModelChange,
          content: data[i].content
        });
      }
    });
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
      this.$indicator.open({
        text: "生成中...",
        spinnerType: "fading-circle"
      });
      this.readWorkbookFromLocalFile(
        document.getElementById("excelInputFile").files[0],
        workbook => {
          var sheetNames = workbook.SheetNames; // 工作表名称集合
          var worksheet = workbook.Sheets[sheetNames[0]]; // 这里我们只读取第一张sheet
          var datajson = XLSX.utils.sheet_to_json(worksheet);
          var myList = new Array();
          for (var i = 0; i < datajson.length; i++) {
            myList.push(datajson[i][this.columnName]);
          }
          this.phones = myList.join(",");
          this.buildHref();
          this.$indicator.close();
          this.ready = true;
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
      saveLink({ phones: this.phones, body: this.msg })
        .then(() => {
          this.$toast("保存成功");
        })
        .catch(() => {
          this.$toast("保存失败");
        });
    },
    getLink() {
      getLink()
        .then(res => {
          console.log(res);
          var data = res.body;
          this.phones = data.phones;
          this.msg = data.body;
          this.ready = true;
          this.$toast("获取成功");
        })
        .catch(err => {
          console.log(err);
          this.$toast("获取失败");
        });
    },
    onModelChange(item) {
      this.$messagebox.confirm('“短信内容”将被覆盖?').then(action =>{
        this.msg = item.content;
        this.modelName = item.name;
        this.sheetVisible=false;
      }).catch(()=>{});
    }
  }
};
</script>
<style scoped>
.main {
  margin-top: 40px;
}
.mint-popup-3 {
  width: 100%;
  height: 100%;
  background-color: #fff;
}
.action-button-container {
  background-color: #fff;
  box-sizing: border-box;
  color: inherit;
  min-height: 48px;
  display: block;
  overflow: hidden;
  position: relative;
  text-decoration: none;
}
.action-button-wrapper {
  background-image: -webkit-linear-gradient(
    top,
    #d9d9d9,
    #d9d9d9 50%,
    transparent 50%
  );
  background-image: linear-gradient(
    180deg,
    #d9d9d9,
    #d9d9d9 50%,
    transparent 50%
  );
  background-size: 120% 1px;
  background-repeat: no-repeat;
  background-position: top left;
  background-origin: content-box;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  box-sizing: border-box;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  font-size: 16px;
  line-height: 1;
  min-height: inherit;
  overflow: hidden;
  padding: 0 10px;
  width: 100%;
}
.action-button {
  display: block;
  width: 70%;
  text-align: center;
  margin: 5px auto;
}
.action-button-inline {
  display: inline-block;
  width: 70%;
  text-align: center;
  margin: 5px auto;
}
</style>