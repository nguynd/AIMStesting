import React, { useState, useEffect } from 'react';
import apiClient from '../services/api'; // Import apiClient đã tạo

function ProductListPage() {
  // 'products' là một state để lưu trữ danh sách sản phẩm lấy về
  const [products, setProducts] = useState([]);
  // 'loading' là state để hiển thị trạng thái đang tải
  const [loading, setLoading] = useState(true);
  // 'error' là state để lưu lỗi nếu có
  const [error, setError] = useState(null);

  // useEffect sẽ chạy một lần sau khi component được render lần đầu tiên
  useEffect(() => {
    // Định nghĩa một hàm async để gọi API
    const fetchProducts = async () => {
      try {
        // Gọi API để lấy danh sách sách (tạm thời)
        const response = await apiClient.get('/books'); 
        setProducts(response.data); // Lưu dữ liệu vào state
      } catch (err) {
        setError(err.message); // Lưu lỗi nếu có
      } finally {
        setLoading(false); // Dừng hiển thị trạng thái đang tải
      }
    };

    fetchProducts(); // Gọi hàm để bắt đầu lấy dữ liệu
  }, []); // Mảng rỗng [] đảm bảo useEffect chỉ chạy 1 lần

  // Xử lý các trạng thái giao diện
  if (loading) return <p>Đang tải sản phẩm...</p>;
  if (error) return <p>Lỗi: {error}</p>;

  return (
    <div>
      <h2>Danh sách Sản phẩm</h2>
      {products.length > 0 ? (
        <ul>
          {products.map(product => (
            <li key={product.id}>
              {product.title} - {product.price} VND
            </li>
          ))}
        </ul>
      ) : (
        <p>Không có sản phẩm nào.</p>
      )}
    </div>
  );
}

export default ProductListPage;