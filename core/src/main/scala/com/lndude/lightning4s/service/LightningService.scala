package com.lndude.lightning4s.service

import com.lndude.lightning4s.dto.requests._
import com.lndude.lightning4s.dto._

trait LightningService {
  def currentBalance(): Balance
  def connect(params: ConnectParams): Boolean
  def disconnect(params: DisconnectParams): Boolean
  def openChannel(params: OpenChannelParams): TransactionId
  def closeChannel(params: CloseChannelParams): TransactionId
  def channelInfo(params: ChannelInfoParams): ChannelInfo
  def createInvoice(params: CreateInvoiceParams): Invoice
  def payInvoice(invoice: Invoice): Boolean
}
