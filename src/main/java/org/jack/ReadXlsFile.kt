package org.jack

import com.google.common.collect.Lists
import com.google.gson.GsonBuilder

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.File

object Constants {
  internal val PATH = "/home/drjr/桌面/yizhifu/银行编码.xlsx"
  internal val GSON = GsonBuilder().disableHtmlEscaping().create()
}

class ReadXlsFile {

  /**
   * parse excel file input to json array
   */
  fun parse(): List<String> {
    val workbook = WorkbookFactory.create(File(Constants.PATH))
    val contents = Lists.newArrayList<String>()

    val dataSheet = workbook.getSheetAt(0)
    val rowIterator = dataSheet.rowIterator()
    var firstRow = true
    while (rowIterator.hasNext()) {
      if (firstRow) { firstRow = false; continue }
      val row = rowIterator.next()
      val line = StringBuilder()
      for (cell in row) {
        line.append(getCellValue(cell))
      }
      contents.add(line.toString())
    }
    return contents
  }

  private fun getCellValue(cell: Cell): String {
    return when (cell.cellType) {
      Cell.CELL_TYPE_STRING -> cell.stringCellValue.replace(",", "")
      Cell.CELL_TYPE_NUMERIC -> cell.numericCellValue.toString()
      else -> cell.stringCellValue
    }
  }

}