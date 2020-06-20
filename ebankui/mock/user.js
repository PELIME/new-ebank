
const tokens = {
  admin: {
    token: 'admin-token'
  },
  editor: {
    token: 'editor-token'
  }
}

const users = {
  'admin-token': {
    roles: ['admin'],
    introduction: 'I am a super administrator',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Super Admin',
    password:'123456'
  },
  'editor-token': {
    roles: ['editor'],
    introduction: 'I am an editor',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Normal Editor'
  }
}
const authinfo={
  "admin":{
    password:'123456'
  },
  "editor":{
    password:"123"
  }
}
module.exports = [
  // user login
  {
    url: '/api/auth/login',
    type: 'post',
    response: config => {
      const { username, password} = config.body;
      if(!authinfo[username]||authinfo[username].password!=password){
        return {
          code: 60204,
          message: '账号或密码错误'
        }
      }
      const token = tokens[username]
      // mock error
      if (!token) {
        return {
          code: 60204,
          message: '登录失败'
        }
      }

      return {
        code: 20000,
        data: token
      }
    }
  },

  // get user info
  {
    url: '/api/user/userinfo',
    type: 'get',
    response: config => {
      const { token } = config.query
      const info = users[token]

      // mock error
      if (!info) {
        return {
          code: 50008,
          message: 'Login failed, unable to get user details.'
        }
      }

      return {
        code: 20000,
        data: info
      }
    }
  },

  // user logout
  {
    url: '/vue-admin-template/user/logout',
    type: 'post',
    response: _ => {
      return {
        code: 20000,
        data: 'success'
      }
    }
  }
]
