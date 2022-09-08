export default class Pageable {
  content = [];
  last = false;
  totalPages = 0;
  totalElements = 0;
  pageNumber = 0;
  size = 0;
  numberOfElements = 0;

  constructor(
    content,
    last,
    totalPages,
    totalElements,
    pageNumber,
    size,
    numberOfElements
  ) {
    this.content = content;
    this.last = last;
    this.totalPages = totalPages;
    this.totalElements = totalElements;
    this.pageNumber = pageNumber;
    this.size = size;
    this.numberOfElements = numberOfElements;
  }
}
