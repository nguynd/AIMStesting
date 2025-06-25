import React from 'react';

// ... (Giữ nguyên các style của bạn)
const cardStyles = { /* ... */ };
const buttonStyles = { /* ... */ };

// Component giờ sẽ nhận một hàm 'onCardClick'
function ProductCard({ product, imageSrc, onCardClick }) {
  return (
    // Thêm sự kiện onClick vào thẻ div chính
    <div style={cardStyles} onClick={() => onCardClick(product)}>
      <img src={imageSrc} alt={product.title} style={{ width: '100%', height: '200px', objectFit: 'cover' }} />
      <h3>{product.title}</h3>
      <p>{product.category}</p>
      <p>{product.price.toLocaleString('vi-VN')} VND</p>
      {/* Ngăn sự kiện click của nút lan ra thẻ div cha */}
      <button style={buttonStyles} onClick={(e) => e.stopPropagation()}>Thêm vào giỏ</button>
    </div>
  );
}

export default ProductCard;