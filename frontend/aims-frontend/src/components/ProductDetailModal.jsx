import React from 'react';

// --- CSS Styles ---
const modalOverlayStyles = {
  position: 'fixed',
  top: 0,
  left: 0,
  right: 0,
  bottom: 0,
  backgroundColor: 'rgba(0, 0, 0, 0.7)',
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
  zIndex: 1000,
};
const modalContentStyles = {
  backgroundColor: 'white',
  padding: '2rem',
  borderRadius: '8px',
  maxWidth: '800px',
  width: '90%',
  display: 'flex',
  gap: '2rem',
};
const closeButtonStyles = {
  position: 'absolute',
  top: '10px',
  right: '15px',
  fontSize: '1.5rem',
  cursor: 'pointer',
  border: 'none',
  background: 'none'
};
const imageStyles = {
    maxWidth: '300px',
    borderRadius: '8px',
  };
// --- End CSS Styles ---

function ProductDetailModal({ product, onClose }) {
  if (!product) return null; // Nếu không có sản phẩm nào được chọn thì không hiển thị

  const imageSrc = `/src/assets/images/${product.imageUrl}`;

  return (
    <div style={modalOverlayStyles} onClick={onClose}>
      <div style={modalContentStyles} onClick={e => e.stopPropagation()}>
        <button style={closeButtonStyles} onClick={onClose}>&times;</button>
        <img src={imageSrc} alt={product.title} style={imageStyles} />
        <div>
          <h1>{product.title}</h1>
          <p style={{ color: '#777' }}>{product.category}</p>
          <p>{product.description || 'Chưa có mô tả cho sản phẩm này.'}</p>
          <h2>{product.price.toLocaleString('vi-VN')} VND</h2>
          <p>Số lượng còn lại: {product.quantity}</p>
          {/* Nút này sẽ có chức năng sau */}
          <button>Thêm vào giỏ hàng</button>
        </div>
      </div>
    </div>
  );
}

export default ProductDetailModal;