import request from '@/utils/request'
export function saveLink(data) {
    return request({
        url: '/api/phonemsg/savelink',
        method: 'post',
        data
    })
}

export function getLink() {
    return request({
        url: '/api/phonemsg/getlink',
        method: 'get'
    })
}

export function getMessageModels(){
    return request({
        url:'/api/phonemsg/messagemodel',
        method:'get'
    })
}