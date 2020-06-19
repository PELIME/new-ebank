import request from '@/utils/request'
export function saveLink(data) {
    return request({
        url: '/ebank/phonemessage/savelink',
        method: 'post',
        data
    })
}

export function getLink() {
    return request({
        url: '/ebank/phonemessage/getlink',
        method: 'get'
    })
}