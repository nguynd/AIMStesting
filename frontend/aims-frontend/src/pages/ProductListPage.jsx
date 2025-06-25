import React, { useState, useEffect } from 'react';
import apiClient from '../services/api';
import ProductCard from '../components/ProductCard'; // Import component mới

// CSS cho layout dạng lưới
const productGridStyles = {
  display: 'flex',
  flexWrap: 'wrap',
  justifyContent: 'center', // hoặc 'flex-start'
  gap: '1rem'
};

function ProductListPage() {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
  const fetchProducts = async () => {
    try {
      // Dùng Promise.all để gọi nhiều API cùng lúc
      const [booksResponse, cdsResponse] = await Promise.all([
        apiClient.get('/books'),
        apiClient.get('/cds')
        // Thêm các lời gọi API cho DVD, LP ở đây nếu bạn muốn
      ]);

      // Gộp kết quả từ các API lại thành một danh sách duy nhất
      const allProducts = [...booksResponse.data, ...cdsResponse.data];
      setProducts(allProducts);

    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  fetchProducts();
}, []);

  if (loading) return <p>Đang tải sản phẩm...</p>;
  if (error) return <p>Lỗi: {error}</p>;

  return (
    <div>
      <h2>Tất cả Sản phẩm</h2>
      {products.length > 0 ? (
        <div style={productGridStyles}>
          {/* Sử dụng ProductCard để hiển thị mỗi sản phẩm */}
          {products.map(product => (
            <ProductCard key={product.id} product={product} />
          ))}
        </div>
      ) : (
        <p>Không có sản phẩm nào.</p>
      )}
    </div>
  );
}

export default ProductListPage;