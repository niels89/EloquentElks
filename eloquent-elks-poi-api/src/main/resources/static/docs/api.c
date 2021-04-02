#include <enunciate-common.c>
#ifndef DEF_api_ns0_poiGetDto_H
#define DEF_api_ns0_poiGetDto_H

/**
 *  Represents a Point of Interest
 */
struct api_ns0_poiGetDto {

};

/**
 * Reads a PoiGetDto from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The PoiGetDto, or NULL in case of error.
 */
static struct api_ns0_poiGetDto *xmlTextReaderReadNs0PoiGetDtoType(xmlTextReaderPtr reader);

/**
 * Writes a PoiGetDto to XML.
 *
 * @param writer The XML writer.
 * @param _poiGetDto The PoiGetDto to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNs0PoiGetDtoType(xmlTextWriterPtr writer, struct api_ns0_poiGetDto *_poiGetDto);

/**
 * Frees the elements of a PoiGetDto.
 *
 * @param _poiGetDto The PoiGetDto to free.
 */
static void freeNs0PoiGetDtoType(struct api_ns0_poiGetDto *_poiGetDto);

#endif /* DEF_api_ns0_poiGetDto_H */
#ifndef DEF_api_ns0_poiGetDto_M
#define DEF_api_ns0_poiGetDto_M

/**
 * Reads a PoiGetDto from XML. The reader is assumed to be at the start element.
 *
 * @return the PoiGetDto, or NULL in case of error.
 */
static struct api_ns0_poiGetDto *xmlTextReaderReadNs0PoiGetDtoType(xmlTextReaderPtr reader) {
  int status, depth;
  void *_child_accessor;
  struct api_ns0_poiGetDto *_poiGetDto = calloc(1, sizeof(struct api_ns0_poiGetDto));




  return _poiGetDto;
}

/**
 * Writes a PoiGetDto to XML.
 *
 * @param writer The XML writer.
 * @param _poiGetDto The PoiGetDto to write.
 * @return The total bytes written, or -1 on error;
 */
static int xmlTextWriterWriteNs0PoiGetDtoType(xmlTextWriterPtr writer, struct api_ns0_poiGetDto *_poiGetDto) {
  int status, totalBytes = 0, i;
  xmlChar *binaryData;

  return totalBytes;
}

/**
 * Frees the elements of a PoiGetDto.
 *
 * @param _poiGetDto The PoiGetDto to free.
 */
static void freeNs0PoiGetDtoType(struct api_ns0_poiGetDto *_poiGetDto) {
  int i;
}
#endif /* DEF_api_ns0_poiGetDto_M */
