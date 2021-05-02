#include <enunciate-common.c>
#ifndef DEF_api_ns0_propertyFetchDTO_H
#define DEF_api_ns0_propertyFetchDTO_H

/**
 * (no documentation provided)
 */
struct api_ns0_propertyFetchDTO {

};

/**
 * Reads a PropertyFetchDTO from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The PropertyFetchDTO, or NULL in case of error.
 */
static struct api_ns0_propertyFetchDTO *xmlTextReaderReadNs0PropertyFetchDTOType(xmlTextReaderPtr reader);

/**
 * Writes a PropertyFetchDTO to XML.
 *
 * @param writer The XML writer.
 * @param _propertyFetchDTO The PropertyFetchDTO to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNs0PropertyFetchDTOType(xmlTextWriterPtr writer, struct api_ns0_propertyFetchDTO *_propertyFetchDTO);

/**
 * Frees the elements of a PropertyFetchDTO.
 *
 * @param _propertyFetchDTO The PropertyFetchDTO to free.
 */
static void freeNs0PropertyFetchDTOType(struct api_ns0_propertyFetchDTO *_propertyFetchDTO);

#endif /* DEF_api_ns0_propertyFetchDTO_H */
#ifndef DEF_api_ns0_propertyFetchDTO_M
#define DEF_api_ns0_propertyFetchDTO_M

/**
 * Reads a PropertyFetchDTO from XML. The reader is assumed to be at the start element.
 *
 * @return the PropertyFetchDTO, or NULL in case of error.
 */
static struct api_ns0_propertyFetchDTO *xmlTextReaderReadNs0PropertyFetchDTOType(xmlTextReaderPtr reader) {
  int status, depth;
  void *_child_accessor;
  struct api_ns0_propertyFetchDTO *_propertyFetchDTO = calloc(1, sizeof(struct api_ns0_propertyFetchDTO));




  return _propertyFetchDTO;
}

/**
 * Writes a PropertyFetchDTO to XML.
 *
 * @param writer The XML writer.
 * @param _propertyFetchDTO The PropertyFetchDTO to write.
 * @return The total bytes written, or -1 on error;
 */
static int xmlTextWriterWriteNs0PropertyFetchDTOType(xmlTextWriterPtr writer, struct api_ns0_propertyFetchDTO *_propertyFetchDTO) {
  int status, totalBytes = 0, i;
  xmlChar *binaryData;

  return totalBytes;
}

/**
 * Frees the elements of a PropertyFetchDTO.
 *
 * @param _propertyFetchDTO The PropertyFetchDTO to free.
 */
static void freeNs0PropertyFetchDTOType(struct api_ns0_propertyFetchDTO *_propertyFetchDTO) {
  int i;
}
#endif /* DEF_api_ns0_propertyFetchDTO_M */
