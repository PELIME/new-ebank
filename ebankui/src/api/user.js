import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/api/auth/login',
    method: 'post',
    contentType:'application/x-www-form-urlencoded',
    data
  })
}

export function getInfo() {
  console.log(localStorage.getItem('ebank_token'))
  return request({
    url: '/api/user/userinfo',
    method: 'get',
    //params: { token }
  })
}

export function logout() {
  return request({
    url: '/ebank/user/logout',
    method: 'post'
  })
}
