import React, { useState, useEffect } from 'react';
import apiClient from '../services/api';
import ProductCard from '../components/ProductCard';

// Import các ảnh đã lưu
import nhaGiaKimImg from '../assets/images/nha-gia-kim.jpg';
import dacNhanTamImg from '../assets/images/dac-nhan-tam.jpg';
import abbaGoldImg from '../assets/images/abba-gold.png';
import notFoundImg from '../assets/images/image-not-found.jpg'; // Cần tạo ảnh này

const productImages = {
  "nha-gia-kim.jpg": nhaGiaKimImg,
  "dac-nhan-tam.jpg": dacNhanTamImg,
  "abba-gold.png": abbaGoldImg
};

const productGridStyles = {
  display: 'flex',
  flexWrap: 'wrap',
  justifyContent: 'center',
  gap: '1rem'
};

function ProductListPage() {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const [booksResponse, cdsResponse] = await Promise.all([
          apiClient.get('/books'),
          apiClient.get('/cds')
        ]);
        
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
          {products.map(product => (
            <ProductCard 
              key={product.id} 
              product={product} 
              // Truyền ảnh đã import vào ProductCard
              imageSrc={productImages[product.imageUrl] || notFoundImg}
            />
          ))}
        </div>
      ) : (
        <p>Không có sản phẩm nào.</p>
      )}
    </div>
  );
}

export default ProductListPage;