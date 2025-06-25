import axios from 'axios';

// Tạo một instance của axios với cấu hình mặc định
const apiClient = axios.create({
  // URL gốc của backend Spring Boot
  baseURL: 'http://localhost:8080/api', 
  headers: {
    'Content-Type': 'application/json',
  },
});

/*
 * INTERCEPTOR (BỘ CAN THIỆP)
 *
 * Sau này, khi chúng ta có chức năng đăng nhập, chúng ta sẽ sử dụng interceptor
 * để tự động đính kèm Token (JWT) vào mỗi request gửi đi.
 * Điều này giúp chúng ta không phải lặp lại việc đính kèm token ở mỗi lời gọi API.
 *
 * Ví dụ:
 * apiClient.interceptors.request.use(config => {
 * const token = localStorage.getItem('token');
 * if (token) {
 * config.headers.Authorization = `Bearer ${token}`;
 * }
 * return config;
 * }, error => {
 * return Promise.reject(error);
 * });
 */

export default apiClient;