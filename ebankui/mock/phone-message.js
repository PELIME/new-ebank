
var save_phones='';
var save_body='';
module.exports=[
    {
        url: '/ebank/phonemessage/savelink',
        type: 'post',
        response: config => {
          const { phones, body} = config.body;
          save_phones=phones;
          save_body=body;
          return {
            code: 20000,
            data: '保存成功'
          }
        }
    },
    {
        url: '/ebank/phonemessage/getlink',
        type: 'get',
        response: () => {
          return {
            code: 20000,
            data: {
                phones:save_phones,
                body:save_body
            }
          }
        }
      },
]